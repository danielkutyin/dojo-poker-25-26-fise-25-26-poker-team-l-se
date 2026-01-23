package fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.Objectives.PandaObjective;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Panda;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class SmartPandaStrategy implements RobotStrategy {
    public Actions choose(TurnView view) {
        Board board = view.board();
        Robot robot = view.robot();

        if (board.getNumTiles() < 3) return Actions.Tiles;

        // 1) Si pas d'objectif panda en main et on peut piocher => on pioche
        if (robot.getPandaObjectives().isEmpty() && robot.canDrawObjective()) {
            return Actions.Objectives;
        }

        // 2) Si déplacer le panda permet de manger un bambou "utile" => Panda
        int bestGain = bestPandaGain(board, robot);
        if (bestGain > 0) return Actions.Panda;

        // 3) Sinon on fait simple
        if (robot.canDrawObjective()) return Actions.Objectives;
        return Actions.Tiles;
    }

    private int bestPandaGain(Board board, Robot robot) {
        List<Position> reachable = reachablePositionsForPanda(board);
        if (reachable.isEmpty()) return 0;

        Map<TileColor, Integer> missing = missingForBestPandaObjective(robot);

        int best = 0;
        for (Position p : reachable) {
            Tile t = board.getTileAt(p);
            if (t == null) continue;
            if (t.getColor() == TileColor.POND) continue;
            if (t.getNbBambous() <= 0) continue;

            // gain = 1 si on mange un bambou utile (couleur manquante), sinon 0
            int gain = (missing.getOrDefault(t.getColor(), 0) > 0) ? 1 : 0;
            best = Math.max(best, gain);
        }
        return best;
    }

    // On prend l'objectif panda "le plus urgent" (le moins de bambous manquants)
    private Map<TileColor, Integer> missingForBestPandaObjective(Robot robot) {
        Map<TileColor, Integer> bestMissing = new EnumMap<>(TileColor.class);
        int bestTotalMissing = Integer.MAX_VALUE;

        for (PandaObjective obj : robot.getPandaObjectives()) {
            Map<TileColor, Integer> req = obj.getRequired(); // <-- supposé exister
            int totalMissing = 0;

            Map<TileColor, Integer> missing = new EnumMap<>(TileColor.class);
            for (Map.Entry<TileColor, Integer> e : req.entrySet()) {
                TileColor c = e.getKey();
                if (c == TileColor.POND) continue;
                int need = e.getValue();
                int have = robot.getEaten(c);
                int m = Math.max(0, need - have);
                missing.put(c, m);
                totalMissing += m;
            }

            if (totalMissing < bestTotalMissing) {
                bestTotalMissing = totalMissing;
                bestMissing = missing;
            }
        }

        return bestMissing;
    }

    private List<Position> reachablePositionsForPanda(Board board) {
        Panda panda = board.getPanda();
        Position start = panda.getPos();

        List<Position> res = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            int max = board.rangeMovement(start, dir);
            for (int dist = 1; dist <= max; dist++) {
                Position p = Direction.move(start, dir, dist);
                if (board.isExistInTiles(p)) res.add(p);
            }
        }
        return res;
    }
}
