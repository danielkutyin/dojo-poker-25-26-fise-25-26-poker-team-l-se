package fr.cotedazur.univ.polytech.startingpoint;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TuileTest {
    @Test
    public void testTileColorAndcolor(){
        Position pos = new Position(2,-1);
        Tile t=new Tile(pos, TileColor.GREEN);
        assertEquals(pos,t.getPosition());
        assertEquals(TileColor.GREEN,t.getColor());
    }
    /** Bambou qui pousse sur une tuile normale **/

    @Test
    public void addBambouOnNonPondTile() {
        Tile tile = new Tile(new Position(1, 0), TileColor.GREEN);

        assertEquals(0, tile.getNbBambous(), "Au début il ne doit pas y avoir de bambou");

        tile.addBambou();

        assertEquals(1, tile.getNbBambous(), "Après addBambou il doit y avoir 1 bambou");
    }


    /** Maximum 4 bambous **/

    @Test
    public void maxFourBambooOnTile() {
        Tile tile = new Tile(new Position(1, 0), TileColor.YELLOW);

        for (int i = 0; i < 10; i++) {
            tile.addBambou();
        }

        assertEquals(4, tile.getNbBambous(), "Une tuile ne doit pas avoir plus de 4 bambous");
    }




}
