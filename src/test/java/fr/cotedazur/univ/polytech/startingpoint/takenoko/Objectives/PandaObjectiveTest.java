package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PandaObjectiveTest {
    @Test
    void notAchievedWhenMissing() {
        PandaObjective obj = new PandaObjective(Map.of(TileColor.GREEN, 2), 3);

        EnumMap<TileColor, Integer> eaten = new EnumMap<>(TileColor.class);
        eaten.put(TileColor.GREEN, 1);

        assertFalse(obj.isAchieved(eaten));
    }

    @Test
    void achievedWhenExact() {
        PandaObjective obj = new PandaObjective(Map.of(TileColor.YELLOW, 2), 4);

        EnumMap<TileColor, Integer> eaten = new EnumMap<>(TileColor.class);
        eaten.put(TileColor.YELLOW, 2);

        assertTrue(obj.isAchieved(eaten));
    }

    @Test
    void achievedWhenSurplus() {
        PandaObjective obj = new PandaObjective(Map.of(TileColor.PINK, 2), 5);

        EnumMap<TileColor, Integer> eaten = new EnumMap<>(TileColor.class);
        eaten.put(TileColor.PINK, 4);

        assertTrue(obj.isAchieved(eaten));
    }

    @Test
    void achievedWhenMultiColors() {
        PandaObjective obj = new PandaObjective(Map.of(
                TileColor.GREEN, 1,
                TileColor.YELLOW, 1,
                TileColor.PINK, 1
        ), 6);

        EnumMap<TileColor, Integer> eaten = new EnumMap<>(TileColor.class);
        eaten.put(TileColor.GREEN, 1);
        eaten.put(TileColor.YELLOW, 1);
        eaten.put(TileColor.PINK, 1);

        assertTrue(obj.isAchieved(eaten));
    }
}
