package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;


import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileLayer;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TilePatternType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileLayerTest {

    private TileLayer layer= new TileLayer();
    @Test
    void doublePatternHasTwoTiles(){
        List<Position> p= layer.getTileLayer(TilePatternType.DOUBLE);
        assertEquals(2,p.size());
        assertTrue(p.contains(new Position(0,0)));
    }

    @Test
    void trianglePatternHasThreeTiles(){
        List<Position> p= layer.getTileLayer(TilePatternType.TRIANGLE);
        assertEquals(3,p.size());
        assertTrue(p.contains(new Position(0,0)));
    }

    @Test
    void squarePatternHasFourTiles(){
        List<Position> p= layer.getTileLayer(TilePatternType.SQUARE);
        assertEquals(4,p.size());
        assertTrue(p.contains(new Position(0,0)));
    }

    @Test
    void snakePatternHasFourTiles(){
        List<Position> p= layer.getTileLayer(TilePatternType.SNAKE);
        assertEquals(3,p.size());
        assertTrue(p.contains(new Position(0,0)));
    }

    @Test
    void straightPatternHasFourTiles(){
        List<Position> p= layer.getTileLayer(TilePatternType.STRAIGHT);
        assertEquals(3,p.size());
        assertTrue(p.contains(new Position(0,0)));
    }

    @Test
    void allPattenrReturnDistinctTiles(){
        for(TilePatternType type: TilePatternType.values()){
            List<Position> p = layer.getTileLayer(type);
            Set<Position> unique = new HashSet<>(p);
            assertEquals(p.size(),unique.size());
        }
    }
}
