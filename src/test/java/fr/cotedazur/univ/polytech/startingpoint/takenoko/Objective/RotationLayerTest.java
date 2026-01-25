package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.RotationLayer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RotationLayerTest {

    private RotationLayer layer= new RotationLayer();

    @Test
    void rotate60AroundOrigin(){
        Position p = new Position(1,-1);
        Position rotated= layer.rotate60(p);
        assertEquals(new Position(1,0), rotated);
    }

    @Test
    void rotatePatterTraingleOneTurn() {
        List<Position> triangle = List.of(new Position(0, 0), new Position(1, -1), new Position(1, 0));
        List<Position> rotation1 = layer.rotateLayer(triangle, 1);

        assertTrue(rotation1.contains(new Position(0, 0)));
        assertTrue(rotation1.contains(new Position(1, 0)));
        assertTrue(rotation1.contains(new Position(0, 1)));
        assertEquals(3, rotation1.size());
    }

    @Test
    void allRotationsReturn6Layers(){
        List<Position> base=List.of(
                new Position(0,0),
                new Position(1,-1)
        );
        List<List<Position>> rotations=layer.allRotations(base);

        assertEquals(6,rotations.size());
        assertEquals(base.size(),rotations.get(0).size());
        assertTrue(rotations.get(0).containsAll(base));
    }

    @Test
    void anchorLayerRelativeLayer(){
        List<Position> rel= List.of(
                new Position(0,0), new Position(1,-1), new Position(1,0)
        );
        Position anchor= new Position(5,-2);
        List<Position> abs=layer.anchorLayer(rel,anchor);
        assertTrue(abs.contains(new Position(5,-2)));
        assertTrue(abs.contains(new Position(6,-3)));
        assertTrue(abs.contains(new Position(6,-2)));
        assertEquals(3,abs.size());
    }
}
