package fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Direction;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TurnView;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Panda;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasicGardnerStrategy implements RobotStrategy{
    public Actions choose(TurnView view) throws Exception {
        Board board = view.board();
        Robot robot = view.robot();

        if (board.getNumTiles() < 3) return Actions.Tiles;

        if (robot.getGardenerObjectives().isEmpty() && robot.canDrawObjective()) {
            return Actions.Objectives;
        }
        if (chooseGardenerDestination(view).isPresent()) return Actions.Panda;

        if (robot.canDrawObjective()) return Actions.Objectives;

        return Actions.Tiles;

    }

    public Optional<Position> chooseGardenerDestination(TurnView view) throws Exception{
        Board board = view.board();
        Robot robot = view.robot();

        List<Position> reachable = reachablePositionsForGardner(board);
        List<Position> allPossible = new ArrayList<>();
        List<Integer> allPossibleNeighboursColor = new ArrayList<>();

        List<Position> allPossibleOnlyOne = new ArrayList<>();


        for(Position p : reachable){
            int neighboursSameColor=0;
            if(board.getTileAt(p) != board.getPond() && board.getTileAt(p).getNbBambous() ==0){
                for( Direction d : Direction.values()){
                    if(board.getTileAt(Direction.move(p,d,1)).getColor().equals(board.getTileAt(p).getColor())) neighboursSameColor++;
                }
                if(neighboursSameColor >= 2) {
                    allPossible.add(p);
                    allPossibleNeighboursColor.add(neighboursSameColor);
                }
                if(neighboursSameColor ==1){
                    allPossibleOnlyOne.add(p);
                }
            }
            neighboursSameColor =0;
        }
        if(allPossibleNeighboursColor.isEmpty()) return Optional.ofNullable(allPossibleOnlyOne.getFirst());
        return Optional.ofNullable(allPossible.get(maxPosition(allPossibleNeighboursColor)));
    }
    private List<Position> reachablePositionsForGardner(Board board) {
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
    private int maxPosition(List<Integer> list){
        int max = list.getFirst();
        for(int i : list){
            if(max < i) max=i;
        }
        return max;
    }
}
