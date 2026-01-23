package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Direction;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileLayer {
    public TileLayer(){;}

    private List<Position> positions;

    public List<Tile> DoubleLayer(Tile tile){
        List<Tile> doubleLayer = new ArrayList<>();
        doubleLayer.add(tile);
        doubleLayer.add(new Tile(Direction.move(tile.getPosition(),Direction.HautDroite,1),tile.getColor()));
        return doubleLayer;
    }
    public List<Tile> TriangleLayer(Tile tile){
        List<Tile> triangleLayer = DoubleLayer(tile);
        triangleLayer.add(new Tile(Direction.move(tile.getPosition(),Direction.Droite,1),tile.getColor()));
        return triangleLayer;
    }
    public List<Tile> SquareLayer(Tile tile){
        List<Tile> squareLayer = TriangleLayer(tile);
        squareLayer.add(new Tile(Direction.move(tile.getPosition(),Direction.BasDroite,1),tile.getColor()));
        return squareLayer;
    }
    public List<Tile> StraightLayer(Tile tile){
        List<Tile> straightLayer = DoubleLayer(tile);
        straightLayer.add(new Tile(Direction.move(tile.getPosition(),Direction.HautDroite,2),tile.getColor()));
        return straightLayer;
    }
    public List<Tile> SnakeLayer(Tile tile){
        List<Tile> snakeLayer = SquareLayer(tile);
        snakeLayer.remove(new Tile(Direction.move(tile.getPosition(),Direction.Droite,1),tile.getColor()));
        return snakeLayer;
    }

}
