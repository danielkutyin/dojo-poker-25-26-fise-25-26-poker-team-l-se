package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RotationLayer {
    public Position rotate60(Position p){
        int q= p.getHeight();
        int r=p.getWidth();
        return new Position(-r,q+r);
    }

    public List<Position> rotateLayer(List<Position> pattern,int times){
        int t=((times %6)+6)%6;
        List<Position> rotated=new ArrayList<>();

        for(Position tile: pattern){
            Position p=tile;
            for(int i=0; i<t; i++){
                p=rotate60(p);
            }
            rotated.add(p);
        }
        return rotated;
    }



    public List<List<Position>> allRotations(List<Position> layer){
        List<List<Position>> rotations = new ArrayList<>();
        for(int t=0; t<6; t++){
            rotations.add(rotateLayer(layer,t));
        }
        return rotations;
    }

    //sert a relativiser les coordonnées des calques sur tout coordonnées
    public List<Position> anchorLayer(List<Position> relativePattern, Position anchor){
        List<Position> abs= new ArrayList<>();
        for(Position rel: relativePattern){
            abs.add(new Position(
                    anchor.getHeight()+rel.getHeight(),
                    anchor.getWidth()+rel.getWidth()
            ));
        }
        return abs;
    }

}
