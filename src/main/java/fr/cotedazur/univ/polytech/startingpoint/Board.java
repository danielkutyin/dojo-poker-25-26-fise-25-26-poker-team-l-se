package fr.cotedazur.univ.polytech.startingpoint;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final Map<Position, Tile> tiles = new HashMap<>();
    private Tile pond;

    public Board() {
        this.pond=new Tile(new Position(0,0), TileColor.POND);
        tiles.put(pond.getPosition(),pond);
    }

    //retourne la tuile etang
    public Tile getPond() {
        return pond;
    }

    //retourne la tuile selon une position donnée
    public Tile getTileAt(Position position) {
        return tiles.get(position);
    }

    //retourne le nombre des tuiles
    public int getNumTiles() {
        return tiles.size();
    }
    public void addTile(Tile tile,Position pos){
        if(tiles.containsKey(pos)) {
            throw new ArgumentalreadyExistOrnotAdj("this tile " + pos.toString() + "already exists");
        }
        boolean isadj=false;
        for (Position p : tiles.keySet()) {
            if (p.isNeighbour(pos)) {
                tiles.put(pos, tile);
                isadj=true;
                break;
            }
        }
        if(!isadj){
            throw new ArgumentalreadyExistOrnotAdj("this tile " + pos.toString() + "can not be added");
        }
    }
    public static Map<Position, Tile> getHashMap(){
        return tiles;
    }

}
