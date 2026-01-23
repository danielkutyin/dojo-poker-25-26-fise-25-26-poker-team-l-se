package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;


import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileLayer;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileObjectives;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileLayerTest {
    @Test
    public void doubleLayerFindTest(){
        TileLayer tileLayer = new TileLayer();
        List<Tile> tileLayerBeforeChange = new ArrayList<>();
        Position position1Duo = new Position(0,0);
        Position position2DuoBottom = new Position(-1,1);
        Tile tile1 = new Tile(position1Duo,TileColor.GREEN);
        Tile tile2 = new Tile(position2DuoBottom,TileColor.GREEN);
        tileLayerBeforeChange.add(tile1);
        tileLayerBeforeChange.add(tile2);

        assertTrue(tileLayer.doubleLayerFind(tileLayerBeforeChange));
    }
}
