package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Direction;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileLayer {
    public TileLayer(){;}

    private List<Position> positions;

    public List<Tile> doubleLayer(Tile tile){
        List<Tile> doubleLayer = new ArrayList<>();
        doubleLayer.add(tile);
        doubleLayer.add(new Tile(Direction.move(tile.getPosition(),Direction.HautDroite,1),tile.getColor()));
        return doubleLayer;
    }
    public List<Tile> triangleLayer(Tile tile){
        List<Tile> triangleLayer = doubleLayer(tile);
        triangleLayer.add(new Tile(Direction.move(tile.getPosition(),Direction.Droite,1),tile.getColor()));
        return triangleLayer;
    }
    public List<Tile> squareLayer(Tile tile){
        List<Tile> squareLayer = triangleLayer(tile);
        squareLayer.add(new Tile(Direction.move(tile.getPosition(),Direction.BasDroite,1),tile.getColor()));
        return squareLayer;
    }
    public List<Tile> straightLayer(Tile tile){
        List<Tile> straightLayer = doubleLayer(tile);
        straightLayer.add(new Tile(Direction.move(tile.getPosition(),Direction.HautDroite,2),tile.getColor()));
        return straightLayer;
    }
    public List<Tile> snakeLayer(Tile tile){
        List<Tile> snakeLayer = squareLayer(tile);
        snakeLayer.remove(new Tile(Direction.move(tile.getPosition(),Direction.Droite,1),tile.getColor()));
        return snakeLayer;
    }
    public boolean doubleLayerFind(List<Tile> list){
        for(int i =0; i< list.size();i++){
            int compteur =0;
            List<Tile> doubleLayer = doubleLayer(list.get(i));
            for(int j =0;j< list.size();j++){
                if(!(list.contains(doubleLayer.get(j)))){
                    break;
                }
                compteur++;
            }
            if (compteur==doubleLayer.size()) return true;
        }
        return false;
    }/// ATTENTION ELLE NE VERIFIE PEUT ETRE PAS TOUTE LES SOLUTIONS A CAUSE DES CALQUES (RAJOUTER AU CALQUE LA ROTATION DE WAWA)
/// peux pas avancer sans avoir la rotation je pense
    public boolean triangleLayerFind(List<Tile> list){
        for(int i =0; i< list.size();i++){
            int compteur =0;
            List<Tile> triangleLayer = triangleLayer(list.get(i));
            for(int j =0;j< list.size();j++){
                if(!(list.contains(triangleLayer.get(j)))){
                    break;
                }
                compteur++;
            }
            if (compteur==triangleLayer.size()) return true;
        }
        return false;
    }
}
