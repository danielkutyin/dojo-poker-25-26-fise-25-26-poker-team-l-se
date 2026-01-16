package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;

import java.util.List;

public class TileObjectives {
    private Tile tuile;
    private Board board;
    private TileLayer layer;

    /// Méthode boolean qui retourne si oui ou non le calque s'applique en
    ///utilisant une rotation aussi pour courvrir tout loes sens

    public boolean testLayer(){
        for (Tile tile : board.getTiles()){
            switch (//méthode qui verifie le calque dans tout le board)
        }
    }
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

}
