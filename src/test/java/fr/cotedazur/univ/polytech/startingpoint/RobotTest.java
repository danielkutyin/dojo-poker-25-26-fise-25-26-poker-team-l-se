package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RobotTest {

    @Test
    void testerRobot(){

        Board.getHashMap().clear();
        Board board = new Board();
        Engine engine = new Engine(board);
        Robot robot = new Robot(engine);

        int height = 1;
        int width = 2;
        TileColor color = TileColor.GREEN;

        robot.placeTileAt(height, width, color);
        assertEquals(2, board.getNumTiles(), "Le plateau doit contenir 2 tuiles (l'étang + la tuile placée par le robot)");
        Position position = new Position(height, width);
        Tile tile = Board.getHashMap().get(position);

        assertNotNull(tile, "Une tuile doit être présente à la position (1,2)");
        assertEquals(TileColor.GREEN, tile.getColor(),
                "La tuile placée par le robot doit être de couleur GREEN");
    }
}
