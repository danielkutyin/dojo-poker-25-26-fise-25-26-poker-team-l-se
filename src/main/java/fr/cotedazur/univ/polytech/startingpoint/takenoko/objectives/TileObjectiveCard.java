package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileObjectiveCard {
    private TilePatternType patternType;
    private TileColor color;
    private int points;
    private Map<Position,TileColor> requiredColors;

    public TileObjectiveCard(TilePatternType patternType , TileColor color, int points ){
        this.patternType=patternType;
        this.color=color;
        this.points=points;
        this.requiredColors=null;
    }

    public TileObjectiveCard(TilePatternType patternType , int points , Map<Position, TileColor> requiredColors ){
        this.patternType=patternType;
        this.color=null;
        this.points=points;
        this.requiredColors=new HashMap<>(requiredColors);
    }

    public TilePatternType getPatternType(){
        return patternType;
    }

    public TileColor getColor(){
        return color;
    }

    public int getPoints(){
        return points;
    }
    public boolean isBiColor(){
        return requiredColors != null;
    }

    public Map<Position, TileColor> getRequiredColors() {
        return requiredColors;
    }

    public Map<Position, TileColor> buildRequiredColors(List<Position> basePattern) {
        if (requiredColors != null) return requiredColors;

        Map<Position, TileColor> map = new HashMap<>();
        for (Position p : basePattern) {
            map.put(p, color);
        }
        return map;
    }

    @Override
    public String toString(){
        return "TileObjectiveCard{"+
                "patternType=" + patternType+
                ", color=" + color+
                ", points="+ points+"}";
    }
}
