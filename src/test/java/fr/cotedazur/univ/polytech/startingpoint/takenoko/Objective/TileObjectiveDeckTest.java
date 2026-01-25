package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileObjectiveCard;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileObjectiveDeck;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TilePatternType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileObjectiveDeckTest {

    @Test
    void drawReturnsEmptyWhenDEckIsEmpty(){
        TileObjectiveDeck deck = new TileObjectiveDeck(new ArrayList<>());
        Optional<TileObjectiveCard> card = deck.draw();
        assertTrue(card.isEmpty());
        assertTrue(deck.isEmpty());
        assertEquals(0,deck.remainingCount());
    }

    @Test
    void drawRemovesCardFromDeck(){
        TileObjectiveCard c1= new TileObjectiveCard(TilePatternType.DOUBLE, TileColor.GREEN,2);
        TileObjectiveCard c2= new TileObjectiveCard(TilePatternType.TRIANGLE    , TileColor.YELLOW,3);
        TileObjectiveDeck deck= new TileObjectiveDeck(List.of(c1,c2));
        int before = deck.remainingCount();
        Optional<TileObjectiveCard> drawn = deck.draw();
        assertTrue(drawn.isPresent());
        assertEquals(before-1,deck.remainingCount());
    }

    @Test
    void drawingAllCardsUntillEmptyDeck(){
        TileObjectiveCard c1= new TileObjectiveCard(TilePatternType.DOUBLE, TileColor.GREEN,2);
        TileObjectiveCard c2= new TileObjectiveCard(TilePatternType.TRIANGLE    , TileColor.YELLOW,3);
        TileObjectiveDeck deck= new TileObjectiveDeck(List.of(c1,c2));

        deck.draw();
        deck.draw();
        assertTrue(deck.draw().isEmpty());
        assertTrue(deck.isEmpty());
        assertEquals(0,deck.remainingCount());
    }

}
