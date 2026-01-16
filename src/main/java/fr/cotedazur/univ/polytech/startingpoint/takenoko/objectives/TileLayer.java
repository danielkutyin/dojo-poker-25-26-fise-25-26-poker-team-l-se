package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Direction;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileLayer {
    public TileLayer(){;}

    public List<Position> DoubleLayer(Position pos){
        List<Position> doubleLayer = new ArrayList<>();
        doubleLayer.add(pos);
        doubleLayer.add(Direction.move(pos,Direction.HautDroite,1));
        return doubleLayer;
    }
    public List<Position> TriangleLayer(Position pos){
        List<Position> triangleLayer = DoubleLayer(pos);
        triangleLayer.add(Direction.move(pos,Direction.Droite,1));
        return triangleLayer;
    }
    public List<Position> SquareLayer(Position pos){
        List<Position> squareLayer = TriangleLayer(pos);
        squareLayer.add(Direction.move(pos,Direction.BasDroite,1));
        return squareLayer;
    }
    public List<Position> StraightLayer(Position pos){
        List<Position> straightLayer = DoubleLayer(pos);
        straightLayer.add(Direction.move(pos,Direction.HautDroite,2));
        return straightLayer;
    }
}
