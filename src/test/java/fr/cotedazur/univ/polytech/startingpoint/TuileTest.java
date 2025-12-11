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
}
