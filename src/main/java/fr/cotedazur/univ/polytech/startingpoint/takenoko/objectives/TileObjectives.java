package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

public class TileObjectives {
    private TileLayer layer= new TileLayer();
    private RotationLayer rotation= new RotationLayer();


    public boolean matches(Board board , List<Position> absPosition, List<Position> rotRelPositions, Map<Position, TileColor> requiredRelColors){
        for (int i=0; i<absPosition.size();i++){
            Position abs=absPosition.get(i);
            Tile t=board.getTileAt(abs);
            if(t==null) return false;

            Position rel = rotRelPositions.get(i);
            TileColor expected= requiredRelColors.get(rel);
            if(expected==null) return false;
            if(t.getColor()!=expected) return false;
        }
        return true;
    }


    private Map<Position, TileColor> rotateRequiredColors(List<Position> baseRel, List<Position> rotRel, Map<Position, TileColor> baseColors) {
        Map<Position, TileColor> rotated = new HashMap<>();
        for (int i = 0; i < baseRel.size(); i++) {
            rotated.put(rotRel.get(i), baseColors.get(baseRel.get(i)));
        }
        return rotated;
    }

    public boolean isCompleted(Board board, TileObjectiveCard card){
        List<Position> base= layer.getTileLayer(card.getPatternType());
        List<List<Position>> rotations= rotation.allRotations(base);

        Map<Position, TileColor> baseColors = card.buildRequiredColors(base);

        for(Tile anchorTile: board.getTiles()){
            Position anchor=anchorTile.getPosition();

            for(List<Position> rot : rotations){
                List<Position> abs = rotation.anchorLayer(rot,anchor);
                Map<Position,TileColor> rotColors = rotateRequiredColors(base,rot,baseColors);
                if(matches(board, abs, rot, rotColors)){
                    return true;
                }
            }
        }
        return false;
    }
}
