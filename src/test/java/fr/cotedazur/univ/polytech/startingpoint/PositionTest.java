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
    @Test
    public void testverifPositionAvailable(){//Test marche mais faut avoir la bonne place au bon endroit dans le hashMap
        List<Position> neighbours;
        List<Position> neighboursReal =new ArrayList<>();
        Board board = new Board();
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(0,2);
        Tile tile1 =new Tile(pos1,TileColor.GREEN);
        Tile tile2 =new Tile(pos2,TileColor.GREEN);
        board.setTile(tile1,pos1);
        board.setTile(tile2,pos2);
        neighbours=pos2.setNeighbours();
        System.out.println(neighbours);

        Position hautDroite = new Position(3, 3);
        Position hautGauche = new Position(3, 1);
        Position droite = new Position(0, 4);
        Position basGauche = new Position(-3, 1);
        Position basDroite = new Position(-3, 3);

        neighboursReal.add(hautDroite);
        neighboursReal.add(hautGauche);
        neighboursReal.add(droite);
        neighboursReal.add(basDroite);
        neighboursReal.add(basGauche);

        assertEquals(neighboursReal,neighbours);
    }
}
