package fr.cotedazur.univ.polytech.startingpoint.takenoko.board;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Panda;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.exceptions.ArgumentalreadyExistOrnotAdj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    /*@BeforeEach
    public void setup() {
        Board.resetBoard();
    }*/

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
        assertThrows(ArgumentalreadyExistOrnotAdj.class, () -> board.addTile(tile));
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
    @Test
    public void testPandaeatenBambou(){
        Board board = new Board();
        Position right=new Position(0,1);
        Tile tilePondRight=new Tile(right,TileColor.GREEN);
        Panda panda=board.getPanda();
        panda.setPos(right);
        board.addTile(tilePondRight);
        board.getTileAt(right).addBambou();
        assertEquals(1,board.getTileAt(right).getNbBambous());
        board.eatBambouOnPandaTile();
        assertEquals(0,board.getTileAt(right).getNbBambous());
    }
    @Test
    public void testGardenerPlantsBamboo() {
        Board board = new Board();
        Position right = new Position(0,1);
        Tile tilePondRight = new Tile(right, TileColor.GREEN);
        board.getGardener().setPos(right);
        board.addTile(tilePondRight);
        assertEquals(0, board.getTileAt(right).getNbBambous());
        board.plantBambooOnGardenerTile();
        assertEquals(1, board.getTileAt(right).getNbBambous());
    }
    @Test
    public void testGetTileAtNonExistingPosition() {
        Board board = new Board();
        Position nonExistingPos = new Position(5,5);
        assertNull(board.getTileAt(nonExistingPos), "getTileAt should return null for non-existing positions");
    }
    @Test
    public void testIsExistInTiles() {
        Board board = new Board();
        Position pondPos = board.getPond().getPosition();
        assertTrue(board.isExistInTiles(pondPos), "Pond position should exist in tiles");

        Position nonExistingPos = new Position(3,3);
        assertFalse(board.isExistInTiles(nonExistingPos), "Non-existing position should not exist in tiles");
    }

    @Test
    public void testRangeMovement(){
        Board board = new Board();
        Position startPosition = new Position(1,0);
        Position blabla = new Position(0,1);
        Position blablabla = new Position(1,1);
        Position next = new Position(2,0);
        Position bliblibli = new Position(2,1);
        Position nextToNext = new Position(3,0);

        Tile startTile=new Tile(startPosition, TileColor.GREEN);
        Tile blablatile=new Tile(blabla,TileColor.GREEN);
        Tile blablablatile=new Tile(blablabla,TileColor.GREEN);
        Tile nextTile=new Tile(next,TileColor.GREEN);
        Tile blibliblitile=new Tile(bliblibli,TileColor.GREEN);
        Tile nextTonextTile=new Tile(nextToNext,TileColor.GREEN);

        board.addTile(startTile);
        board.addTile(blablatile);
        board.addTile(blablablatile);
        board.addTile(nextTile);
        board.addTile(blibliblitile);
        board.addTile(nextTonextTile);

        assertEquals(2,board.rangeMovement(startPosition,Direction.Droite));
    }
    // Java
    @Test
    public void testPlantBambooOnGardenerTile_onPondDoesNothing() {
        Board board = new Board();
        // Le jardinier est par défaut sur l'étang (0,0) si c'est votre convention
        // On vérifie qu'aucun bambou n'est planté sur l'étang.
        Tile pond = board.getPond();
        assertEquals(TileColor.POND, pond.getColor());
        int before = pond.getNbBambous();

        board.plantBambooOnGardenerTile();

        assertEquals(before, pond.getNbBambous());
    }

    @Test
    public void testPlantBambooOnGardenerTile_onGreenTilePlantsOneBamboo() {
        Board board = new Board();
        Position pos = new Position(0,1); // position valide adjacente à l'étang
        Tile greenTile = new Tile(pos, TileColor.GREEN);
        board.addTile(greenTile);

        // On place le jardinier sur cette tuile
        board.getGardener().setPos(pos);

        assertEquals(0, board.getTileAt(pos).getNbBambous());
        board.plantBambooOnGardenerTile();
        assertEquals(1, board.getTileAt(pos).getNbBambous());
    }


}
