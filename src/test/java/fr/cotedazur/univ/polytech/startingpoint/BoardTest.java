package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    @Test
    public void testBoardStartsWithPond(){
        Board board = new Board();
        //vérifier le nombre de tuiles placés doit etre a 1 lors de l initialisation
        assertEquals(1,board.getNumTiles());
        Tile pond=board.getPond();
        //verifier le renvoi de la couleur et et position de l etang
        assertEquals(TileColor.POND,pond.getColor());
        assertEquals(new Position(0,0),pond.getPosition());

        assertEquals(pond,board.getTileAt(new Position(0,0)));
    }

    @Test
    public void testAddTileOnExistingTile(){
        Board board = new Board();
        Position pondPos = board.getPond().getPosition();
        Tile tile=new Tile(pondPos,TileColor.GREEN);
        assertThrows(ArgumentalreadyExistOrnotAdj.class, () -> board.addTile(tile));
    }

    @Test
    public void testAddTileOnNotAdjacentTile(){
        Board board = new Board();
        Position pos=new Position(0,0);
        Tile tile=new Tile(pos,TileColor.GREEN);
        assertThrows(IllegalArgumentException.class, () -> board.addTile(tile));
    }

}
