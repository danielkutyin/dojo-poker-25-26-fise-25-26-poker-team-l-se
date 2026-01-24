package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class TileObjectives {
    private TileLayer layer= new TileLayer();
    private RotationLayer rotation= new RotationLayer();


    public boolean matches(Board board , List<Position> absPosition, TileColor requiredColor){
        for (Position p: absPosition){
            Tile t = board.getTileAt(p);
            if(t==null) return false;
            if(t.getColor()!=requiredColor) return false;
        }
        return true;
    }

    public boolean isCompleted(Board board, TileObjectiveCard card){
        List<Position> base= layer.getTileLayer(card.getPatternType());
        List<List<Position>> rotations= rotation.allRotations(base);

        for(Tile anchorTile: board.getTiles()){
            Position anchor=anchorTile.getPosition();

            for(List<Position> rot : rotations){
                List<Position> abs = rotation.anchorLayer(rot,anchor);
                if(matches(board, abs, card.getColor())){
                    return true;
                }
            }
        }
        return false;
    }
}
