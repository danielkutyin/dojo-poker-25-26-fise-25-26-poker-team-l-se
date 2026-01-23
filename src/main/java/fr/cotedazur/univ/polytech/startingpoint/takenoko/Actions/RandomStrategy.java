package fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TurnView;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;

import java.util.Random;

public class RandomStrategy implements RobotStrategy{
    private final Random random = new Random();
        @Override
        public Actions choose(TurnView view) {
            Board board = view.board();
            Robot robot = view.robot();

            if (board.getNumTiles() < 3) return Actions.Tiles;

            boolean canObjectives = robot.totalObjectivesInHand() < 5;

            int x = random.nextInt(10);
            if (x < 4) return Actions.Tiles;
            if (x < 7) return Actions.Gardener;
            if (x < 9) return Actions.Panda;
            return canObjectives ? Actions.Objectives : Actions.Tiles;
        }
    }



