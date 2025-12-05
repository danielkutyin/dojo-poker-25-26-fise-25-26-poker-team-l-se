package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RobotTest {

    @Test
    public void testPlaceTileAdjEtangScore(){
        Board board = new Board();
        Robot robot = new Robot(board);
        int initialTiles=board.getNumTiles();
        int initialScore=robot.getScore();

        robot.placeTileAdEtang();
        assertEquals(initialTiles+1,board.getNumTiles());
        assertEquals(initialScore+1,robot.getScore());

    }
}
