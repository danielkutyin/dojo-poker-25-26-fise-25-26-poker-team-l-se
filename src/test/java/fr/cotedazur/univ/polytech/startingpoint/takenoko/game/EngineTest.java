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
    public void testPlayOneTurnAddsTileAndScore(){
        Board board = new Board();
        Robot r1 = new Robot(board);
        Robot r2 = new Robot(board);
        Robot[] robots = {r1,r2};

        Engine engine = new Engine(board,robots);
        int initialTiles= board.getNumTiles();
        int initialScoreR1= r1.getScore();
        engine.playOneTurn(0);
        assertEquals(initialTiles+1,board.getNumTiles());
        assertEquals(initialScoreR1 + 1,r1.getScore());
    }

    @Test
    public void testRunDemoAddsToBoard(){
        Board board = new Board();
        Robot r1 = new Robot(board);
        Robot r2 = new Robot(board);
        Robot[] robots = {r1,r2};
        Engine engine = new Engine(board,robots);
        engine.runDemo();
        assertEquals(14,board.getNumTiles());
        int totalScore= r1.getScore()+r2.getScore();
        assertEquals(board.getNumTiles() - 1,totalScore);
        assertTrue(r1.getScore()>0||r2.getScore()>0);
    }
}
