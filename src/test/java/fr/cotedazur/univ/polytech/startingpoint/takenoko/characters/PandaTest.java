package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PandaTest {
    @Test
    public void pandaStartsOnPondTest() {
        Panda po=new Panda();
        assertEquals(new Position(0,0),po.getPos());
    }

    @Test
    public void pandaPositionCanBeChangedTest() {
        Panda po=new Panda();
        Position pos=new Position(2,-1);
        po.setPos(pos);
        assertEquals(pos,po.getPos());
    }
    @Test
    public void pandaCanEatBambooTest() {
        Board board = new Board();

        // Tuile verte adjacente à l\'étang
        Position pos = new Position(0,1);
        Tile greenTile = new Tile(pos, TileColor.GREEN);
        board.addTile(greenTile);

        // On met un bambou sur la tuile
        greenTile.addBambou();
        assertEquals(1, greenTile.getNbBambous());

        // On place le panda sur cette tuile
        board.getPanda().setPos(pos);

        // Le panda doit manger un bambou
        boolean ate = board.tryEatBambouOnPandaTile();
        assertTrue(ate);
        assertEquals(0, greenTile.getNbBambous());
    }
    @Test
    public void pandaDoesNotEatWhenNoBamboo() {
        Board board = new Board();

        Position pos = new Position(1,0);
        Tile greenTile = new Tile(pos, TileColor.GREEN , Improvements.ENCLOSURE);
        board.addTile(greenTile);

        // Pas de bambou sur la tuile
        assertEquals(0, greenTile.getNbBambous());

        board.getPanda().setPos(pos);

        boolean ate = board.tryEatBambouOnPandaTile();
        assertFalse(ate);
        assertEquals(0, greenTile.getNbBambous());


        greenTile.addBambou();
        assertEquals(1, greenTile.getNbBambous());
        ate = board.tryEatBambouOnPandaTile();
        assertFalse(ate);
        assertEquals(1, greenTile.getNbBambous());
    }



}
