package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class PandaObjective {
    private final EnumMap<TileColor, Integer> required;
    private final int points;


    public PandaObjective(Map<TileColor, Integer> required, int points) {
        this.required = new EnumMap<>(TileColor.class);
        for (TileColor c : TileColor.values()) {
            this.required.put(c, 0);
        }
        for (Map.Entry<TileColor, Integer> e : required.entrySet()) {
            TileColor c = e.getKey();
            if (c != TileColor.POND) {
                this.required.put(c, Math.max(0, e.getValue()));
            }
        }
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public Map<TileColor, Integer> getRequired() {
        return Collections.unmodifiableMap(required);
    }

    public boolean isAchieved(Map<TileColor, Integer> eaten) {
        for (Map.Entry<TileColor, Integer> req : required.entrySet()) {
            TileColor c = req.getKey();
            if (c == TileColor.POND) continue;
            int need = req.getValue();
            int have = eaten.getOrDefault(c, 0);
            if (have < need) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PandaObjective{" + "required=" + required + ", points=" + points + '}';
    }


}
