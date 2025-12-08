package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    public void testEqualsValues(){
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(0,0);
        Position pos3 = new Position(1,0);
        assertEquals(pos1,pos2);
        assertNotEquals(pos1,pos3);
    }

    @Test
    public void testHashCodeValues(){
        Position pos1 = new Position(1,-1);
        Position pos2 = new Position(1,-1);
        assertEquals(pos1,pos2);
        assertEquals(pos1.hashCode(),pos2.hashCode());
    }

    @Test
    public void testadjacency(){
        Position pos1 = new Position(1,1);
        Position pos2 = pos1.getNeighbourPosbyindex(0);
        Position pos3 = pos1.getNeighbourPosbyindex(3);
        assertEquals(new Position(2,1),pos2);
        assertEquals(new Position(0,1),pos3);
    }

    @Test
    public void testadjacency2(){
        Position pos1 = new Position(1,1);
        assertTrue(pos1.isNeighbour(new Position(0,1)));
    }
}
