package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;



import fr.cotedazur.univ.polytech.startingpoint.takenoko.Objectives.PandaObjective;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

import java.util.*;

public class ObjectivesPanda {
    private final List<PandaObjective> deck = new ArrayList<>();
    private final Random random;

    public ObjectivesPanda(Random random) {
        this.random = random;
        deck.addAll(buildAll());
        Collections.shuffle(deck, random);
    }

    private List<PandaObjective> buildAll() {
        List<PandaObjective> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) list.add(new PandaObjective(Map.of(TileColor.GREEN, 2), 3));
        for (int i = 0; i < 4; i++) list.add(new PandaObjective(Map.of(TileColor.YELLOW, 2), 4));
        for (int i = 0; i < 3; i++) list.add(new PandaObjective(Map.of(TileColor.PINK, 2), 5));
        for (int i = 0; i < 3; i++) list.add(new PandaObjective(Map.of(
                TileColor.GREEN, 1,
                TileColor.YELLOW, 1,
                TileColor.PINK, 1
        ), 6));

        return list;
    }

    /** Pioche 1 carte et la retire du deck */
    public Optional<PandaObjective> draw() {
        if (deck.isEmpty()) return Optional.empty();
        return Optional.of(deck.remove(deck.size() - 1)); // pop du haut
    }

    public int remaining() {
        return deck.size();
    }


}
