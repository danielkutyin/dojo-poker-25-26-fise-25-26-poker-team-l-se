package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EngineTest {

    @Test
    void verificationplaceTile(){

        Board.getHashMap().clear(); /** on part d'une map vide **/

        Board board = new Board();
        Engine engine = new Engine(board);
        int height = 1;
        int width = 2;
        TileColor color = TileColor.GREEN;

        Position pos = engine.placeTile(height, width, color);

        Position position = new Position(height, width);


        assertEquals(position, pos, "L'Engine doit renvoyer la Position de la tuile posée");
        assertEquals(2, board.getNumTiles(), "Le plateau doit contenir 2 tuiles (l'étang + la tuile posée)");
        Tile tile = Board.getHashMap().get(position);

        assertNotNull(tile, "Une tuile doit être présente à la position (1,2)");
        assertEquals(TileColor.GREEN, tile.getColor(),  "La tuile placée doit être de couleur GREEN");



    }

}
