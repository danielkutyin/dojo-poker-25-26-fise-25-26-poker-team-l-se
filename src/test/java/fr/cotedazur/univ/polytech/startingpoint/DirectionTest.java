package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class DirectionTest {
    @Test
    public void testMove(){
        Position finalTruePositionReach = new Position(1,0);
        Position finalFalsePositionReach = new Position(1,1);
        Position startPosition = new Position(0,0);
        assertEquals(finalTruePositionReach, Direction.move(startPosition,Direction.Droite));
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

        assertEquals(2,Direction.rangeMovement(startPosition,Direction.Droite));
    }
}
