package fr.cotedazur.univ.polytech.startingpoint.takenoko.bamboo;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.elements.Bambou;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BambouTest {
    @Test
    public void testBambouKeepsColor() {
        Bambou b = new Bambou(TileColor.GREEN);
        assertEquals(TileColor.GREEN, b.getColor());
    }

    @Test
    public void testToStringContainsColorName() {
        Bambou b = new Bambou(TileColor.YELLOW);
        String asString = b.toString();
        assertTrue(asString.contains("YELLOW"));
    }
}
