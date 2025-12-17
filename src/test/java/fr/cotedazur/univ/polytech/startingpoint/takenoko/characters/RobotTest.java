package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RobotTest {

    @BeforeEach
    void setup() {
        Board.resetBoard();
    }

    @Test
    public void testPlayTileTurnAddScore(){
        Board board = new Board();
        Robot robot = new Robot(board);
        int initialTiles=board.getNumTiles();
        int initialScore=robot.getScore();

        robot.playTileTurn();
        assertEquals(initialTiles+1,board.getNumTiles());
        assertEquals(initialScore+1,robot.getScore());
    }
    @Test
    public void testPlayTilefirst(){
        Board board = new Board();
        Robot robot = new Robot(board);
        Position initialPos=new Position(0,0);
        assertEquals(initialPos,board.getTileAt(initialPos).getPosition());
        robot.playTurn();
        assertEquals(2,board.getNumTiles());
        robot.playTileTurn();
        assertEquals(3,board.getNumTiles());
    }
    @Test
    public void testPlayPandaTurnAddScore(){
        Board board = new Board();
        Robot robot = new Robot(board);
        int initialScore=robot.getScore();
        robot.playTileTurn();
        robot.playPandaTurn();
        assertEquals(initialScore+1,robot.getScore());
    }




}
