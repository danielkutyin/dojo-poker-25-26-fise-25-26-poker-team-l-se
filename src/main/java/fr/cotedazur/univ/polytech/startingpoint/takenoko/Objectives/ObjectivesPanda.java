package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

import java.util.*;

public class ObjectivesPanda {
    private static final List<PandaObjective> ALL = buildAll();

    private ObjectivesPanda() {}

    private static List<PandaObjective> buildAll() {
        List<PandaObjective> list = new ArrayList<>();
        // 2 GREEN -> 3 points (x5)
        for (int i = 0; i < 5; i++) {
            list.add(new PandaObjective(Map.of(TileColor.GREEN, 2), 3));
        }
        // 2 YELLOW -> 4 points (x4)
        for (int i = 0; i < 4; i++) {
            list.add(new PandaObjective(Map.of(TileColor.YELLOW, 2), 4));
        }
        // 2 PINK -> 5 points (x3)
        for (int i = 0; i < 3; i++) {
            list.add(new PandaObjective(Map.of(TileColor.PINK, 2), 5));
        }
        // 1+1+1 -> 6 points (x3)
        for (int i = 0; i < 3; i++) {
            list.add(new PandaObjective(Map.of(
                    TileColor.GREEN, 1,
                    TileColor.YELLOW, 1,
                    TileColor.PINK, 1
            ), 6));
        }

        return list;
    }
    public static List<PandaObjective> all() {
        return Collections.unmodifiableList(ALL);
    }

    public static PandaObjective drawRandom(Random random) {
        return ALL.get(random.nextInt(ALL.size()));
    }

}
