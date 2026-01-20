package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Direction;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;

import java.util.List;

import static java.lang.Math.abs;

public class TileObjectives {
    private Tile tuile;
    private Board board;
    private TileLayer layer;

    /// Méthode boolean qui retourne si oui ou non le calque s'applique en
    ///utilisant une rotation aussi pour courvrir tout loes sens

//    public boolean testLayer(){
//        for (Tile tile : board.getTiles()){
//            switch (//méthode qui verifie le calque dans tout le board)
//        }
//    }
    public boolean layerMatch(List<Tile> tileLayer,List<Tile> getterTile){
        int compteur=0;
        for(Tile tile : getterTile){
            for(Tile tile2 : tileLayer){
                if(tile.equals(tile2)) compteur++;
            }
        }
        if(compteur==tileLayer.size()) return true;
        return false;
    }
    public List<Tile> rotation(List<Tile> tileLayer,Position pos,Direction direction){
        for(int i =0;i<tileLayer.size();i++){
            if(board.verifTouchBtw2Pos(tileLayer.get(i).getPosition(),pos))//si on est collé ou non
            {
                tileLayer.get(i).setPosition(Direction.move(tileLayer.get(i).getPosition(), direction, 1));;//décalage de 1 car distance de 1
            } else {
                tileLayer.get(i).setPosition(Direction.move(tileLayer.get(i).getPosition(), direction, 3));//décalage de 3 car on est a distance de 2
            }
        }
        return tileLayer;
    }
}
