package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileObjectiveCard;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TilePatternType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileObjectiveCardTest {
    @Test
    void cardStoringCorrectly(){
        TileObjectiveCard card = new TileObjectiveCard(
                TilePatternType.TRIANGLE,
                TileColor.GREEN,
                3
        );
        assertEquals(TilePatternType.TRIANGLE, card.getPatternType());
        assertEquals(TileColor.GREEN,card.getColor());
        assertEquals(3,card.getPoints());
    }

    @Test
    void toStringNotNull(){
        TileObjectiveCard card=new TileObjectiveCard(
                TilePatternType.DOUBLE,
                TileColor.YELLOW,
                2
        );
        assertNotNull(card.toString());
        assertTrue(card.toString().contains("DOUBLE"));
    }
}
