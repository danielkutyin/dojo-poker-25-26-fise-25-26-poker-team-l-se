package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Direction;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.list;

public class TileLayer {

    public List<Position> getTileLayer(TilePatternType layer){
        List<Position> l = new ArrayList<>();
        switch (layer){
            case DOUBLE:
                return list(
                        new Position(0,0), new Position(1,-1));
                        //HautDroite
            case TRIANGLE:
                return list(
                        new Position(0,0),new Position(1,-1), new Position(1,0)
                );
            case SQUARE:
                return list(
                        new Position(0,0),new Position(1,-1),new Position(1,0),new Position(0,1)
                );
            case STRAIGHT:
                return list(
                        new Position(0,0),new Position(1,-1),new Position(2,-2)
                );
            case SNAKE:
                return list(
                        new Position(0,0),new Position(1,-1),new Position(0,1)
                );
            default:
                return new ArrayList<>();
            }
        }

        private List<Position> list(Position... positions){
            List<Position> l=new ArrayList<>();
            for (Position p: positions) l.add(p);
            return l;
        }
}
