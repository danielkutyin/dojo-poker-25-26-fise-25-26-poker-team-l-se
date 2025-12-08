package fr.cotedazur.univ.polytech.startingpoint;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class DirectionTest {
    @Test
    public void testMove(){
        Position finalTruePositionReach = new Position(1,0);
        Position finalFalsePositionReach = new Position(1,1);
        Position startPosition = new Position(0,0);
        assertEquals(finalTruePositionReach,Direction.move(startPosition,Direction.Droite));
        assertNotEquals(finalFalsePositionReach,Direction.move(startPosition,Direction.Droite));
    }
    @Test
    public void testMoveALot(){
        Position finalTruePositionReach = new Position(3,0);
        Position finalFalsePositionReach = new Position(4,0);
        Position startPosition = new Position(0,0);
        assertEquals(finalTruePositionReach,Direction.moveALot(startPosition,Direction.Droite,3));
        assertNotEquals(finalFalsePositionReach,Direction.moveALot(startPosition,Direction.Droite,3));
    }
}
