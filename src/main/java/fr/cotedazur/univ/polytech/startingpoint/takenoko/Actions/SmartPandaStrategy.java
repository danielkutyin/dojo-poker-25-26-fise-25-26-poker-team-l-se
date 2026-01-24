package fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.PandaObjective;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Panda;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;

import java.util.*;

public class SmartPandaStrategy implements RobotStrategy {
    public Actions choose(TurnView view) {
        Board board = view.board();
        Robot robot = view.robot();

        if (board.getNumTiles() < 3) return Actions.Tiles;

        // 1) Si pas d'objectif panda# en main et on peut piocher => on pioche
        if (robot.getPandaObjectives().isEmpty() && robot.canDrawObjective()) {
            return Actions.Objectives;
        }

        // 2) Si déplacer le panda permet de manger un bambou "utile" => Panda
        if (choosePandaDestination(view).isPresent()) return Actions.Panda;

        // 3) Sinon on fait simple
        if (robot.canDrawObjective()) return Actions.Objectives;
        return Actions.Tiles;
    }


    public Optional<Position> choosePandaDestination(TurnView view) {
        Board board = view.board();
        Robot robot = view.robot();
        if (robot.getPandaObjectives().isEmpty()) return Optional.empty(); // pas d'objectif panda


        List<Position> reachable = reachablePositionsForPanda(board);
        if (reachable.isEmpty()) return Optional.empty();

        Map<TileColor, Integer> missing = missingForBestPandaObjective(robot);

        Position bestPos = null;
        int bestScore = Integer.MIN_VALUE;

        for (Position p : reachable) {
            Tile t = board.getTileAt(p);
            if (t == null) continue;
            if (t.getColor() == TileColor.POND) continue;
            if (t.getNbBambous() <= 0) continue; // on veut manger

            int score = 0;
            if (missing.getOrDefault(t.getColor(), 0) > 0) score += 100; // couleur utile
            score += t.getNbBambous(); // tie-breaker

            if (score > bestScore) {
                bestScore = score;
                bestPos = p;
            }
        }

        return Optional.ofNullable(bestPos);
    }


    // On prend l'objectif panda "le plus urgent" (le moins de bambous manquants)
    private Map<TileColor, Integer> missingForBestPandaObjective(Robot robot) {
        Map<TileColor, Integer> bestMissing = new EnumMap<>(TileColor.class);
        int bestTotalMissing = Integer.MAX_VALUE;

        for (PandaObjective obj : robot.getPandaObjectives()) {
            Map<TileColor, Integer> req = obj.getRequired();
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
