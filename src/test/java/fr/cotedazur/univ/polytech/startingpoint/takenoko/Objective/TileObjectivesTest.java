package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileObjectives;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileObjectivesTest {
    private TileObjectives tileObjectives=new TileObjectives();
    @Test
    public void rotationTest(){
        List<Tile> tileLayerBeforeChange = new ArrayList<>();
        Position position1Duo = new Position(0,0);
        Position position2DuoBottom = new Position(-1,1);
        Tile tile1 = new Tile(position1Duo,TileColor.GREEN);
        Tile tile2 = new Tile(position2DuoBottom,TileColor.GREEN);
        /// on effectue ici une rotation vers la droite
        tileLayerBeforeChange.add(tile1);
        tileLayerBeforeChange.add(tile2);

        List<Tile> tileLayerAfterChange = new ArrayList<>();
        Position positionLastDuo = new Position(0,0);
        Position positionLastDuoBottom = new Position(0,1);
        Tile tile3 = new Tile(positionLastDuo,TileColor.GREEN);
        Tile tile4 = new Tile(positionLastDuoBottom,TileColor.GREEN);
        /// on effectue ici une rotation vers la droite
        tileLayerBeforeChange.add(tile3);
        tileLayerBeforeChange.add(tile4);
        assertEquals(tileObjectives.rotation(tileLayerBeforeChange,position1Duo, Direction.Droite),tileLayerAfterChange);
    }


}
