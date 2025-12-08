package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    public void testVerifRulePond(){
        Board board = new Board();
        Position closeToPond = new Position(0,1);
        Position farFromPond = new Position(0,2);
        assertTrue(board.verifRulesPond(closeToPond));
        assertFalse(board.verifRulesPond(farFromPond));
    }

    @Test
    public void testVerifRule2Touch(){
        Board board = new Board();
        Position right=new Position(0,1);
        Tile tilePondRight=new Tile(right,TileColor.GREEN);
        Position top=new Position(1,0);
        Tile tilePondTop=new Tile(top,TileColor.GREEN);

        Position goodPos=new Position(1,1);
        Position badPos=new Position(-1,-1);
        board.addTile(tilePondRight);
        board.addTile(tilePondTop);
        assertTrue(board.verifRule2Touch(goodPos));
        assertFalse(board.verifRule2Touch(badPos));
    }
}
