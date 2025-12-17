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



}
