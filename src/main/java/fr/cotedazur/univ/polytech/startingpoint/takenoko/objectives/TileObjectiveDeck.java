package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

import java.util.*;

public class TileObjectiveDeck {
    private List<TileObjectiveCard> remaining;
    private Random random=new Random();

    public TileObjectiveDeck() {
        this.remaining = new ArrayList<>(createAllDefaultCards());
        Collections.shuffle(this.remaining, random);
    }

    private List<TileObjectiveCard> createAllDefaultCards() {
        List<TileObjectiveCard> cards = new ArrayList<>();

        cards.add(new TileObjectiveCard(TilePatternType.TRIANGLE, TileColor.GREEN, 2));
        cards.add(new TileObjectiveCard(TilePatternType.SQUARE,   TileColor.GREEN, 3));

        cards.add(biColorSquare(5, TileColor.PINK,   TileColor.YELLOW));
        cards.add(biColorSquare(4, TileColor.PINK,   TileColor.GREEN));
        cards.add(biColorSquare(3, TileColor.YELLOW, TileColor.GREEN));

        cards.add(new TileObjectiveCard(TilePatternType.STRAIGHT, TileColor.YELLOW, 3));
        cards.add(new TileObjectiveCard(TilePatternType.SQUARE,   TileColor.YELLOW, 4));
        cards.add(new TileObjectiveCard(TilePatternType.SNAKE,    TileColor.YELLOW, 3));

        cards.add(new TileObjectiveCard(TilePatternType.STRAIGHT, TileColor.GREEN, 2));
        cards.add(new TileObjectiveCard(TilePatternType.SNAKE,    TileColor.GREEN, 2));

        cards.add(new TileObjectiveCard(TilePatternType.STRAIGHT, TileColor.PINK, 4));
        cards.add(new TileObjectiveCard(TilePatternType.TRIANGLE, TileColor.PINK, 4));
        cards.add(new TileObjectiveCard(TilePatternType.SQUARE,   TileColor.PINK, 5));
        cards.add(new TileObjectiveCard(TilePatternType.SNAKE,    TileColor.PINK, 4));

        cards.add(new TileObjectiveCard(TilePatternType.TRIANGLE, TileColor.YELLOW, 3));

        return cards;
    }

    private TileObjectiveCard biColorSquare(int points, TileColor colorA, TileColor colorB) {
        Map<Position, TileColor> req = new HashMap<>();
        req.put(new Position(0, 0),  colorA);
        req.put(new Position(1, -1), colorB);
        req.put(new Position(1, 0),  colorA);
        req.put(new Position(0, 1),  colorB);

        return new TileObjectiveCard(TilePatternType.SQUARE, points, req);
    }


    public boolean isEmpty(){
        return remaining.isEmpty();
    }

    public int remainingCount(){
        return remaining.size();
    }

    public Optional<TileObjectiveCard> draw(){
        if(remaining.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(remaining.remove(remaining.size()-1));
    }
}
