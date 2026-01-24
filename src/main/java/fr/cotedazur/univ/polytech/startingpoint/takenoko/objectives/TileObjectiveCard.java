package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

public class TileObjectiveCard {
    private TilePatternType patternType;
    private TileColor color;
    private int points;

    public TileObjectiveCard(TilePatternType patternType , TileColor color, int points ){
        this.patternType=patternType;
        this.color=color;
        this.points=points;
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

    @Override
    public String toString(){
        return "TileObjectiveCard{"+
                "patternType=" + patternType+
                ", color=" + color+
                ", points="+ points+"}";
    }
}
