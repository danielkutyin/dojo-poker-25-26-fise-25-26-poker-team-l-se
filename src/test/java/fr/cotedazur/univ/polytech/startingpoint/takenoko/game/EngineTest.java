package fr.cotedazur.univ.polytech.startingpoint.takenoko.game;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EngineTest {

    @BeforeEach
    void setup() {
        Board.resetBoard();
    }


    @Test
    public void getWinnerReturnsRobotWithHighestScore() {
        Board board = new Board();
        Robot r1 = new Robot(board);
        Robot r2 = new Robot(board);
        Robot[] robots = {r1,r2};
        Engine engine = new Engine(board,robots);

        r1.addScore(5);
        r2.addScore(2);
        assertEquals(r1.getName(),engine.getWinner().getName());
    }

    @Test
    public void getWinnerTie(){
        Board board = new Board();
        Robot r1 = new Robot(board);
        Robot r2 = new Robot(board);
        Robot[] robots = {r1,r2};
        Engine engine = new Engine(board,robots);
        r1.addScore(5);
        r2.addScore(5);
        //pour le moment le 1er robot reste comme gagnat en cas d egalite
        assertEquals(r1.getName(),engine.getWinner().getName());
    }
    @Test
    public void verifyPalcedTuilFirst(){
        Board board = new Board();
        Robot r1 = new Robot(board);
        Robot r2 = new Robot(board);
        Robot[] robots = {r1,r2};
        Engine engine = new Engine(board,robots);
        assertEquals(1,board.getNumTiles());
    }


}
