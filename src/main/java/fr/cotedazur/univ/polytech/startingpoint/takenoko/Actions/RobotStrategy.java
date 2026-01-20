package fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;

public interface RobotStrategy {
    Actions choose(Robot robot, Board board);
}
