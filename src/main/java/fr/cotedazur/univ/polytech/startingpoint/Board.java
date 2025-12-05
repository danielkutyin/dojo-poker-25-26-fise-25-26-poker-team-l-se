package fr.cotedazur.univ.polytech.startingpoint;

import java.util.*;

public class Board {

    private final Map<Position, Tile> tiles = new HashMap<>();
    private final Tile pond;

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

    public void addTile(Tile tile){
        Position pos=tile.getPosition();
        if(tiles.containsKey(pos)) {
            throw new ArgumentalreadyExistOrnotAdj("this tile " + pos.toString() + "already exists");
        }
        boolean isadj=false;
        for (Position p : tiles.keySet()) {
            if (p.isNeighbour(pos)) {

                isadj=true;
                break;
            }
        }
        if(!isadj){
            throw new ArgumentalreadyExistOrnotAdj("this tile " + pos.toString() + "can not be added");
        }
        tiles.put(pos, tile);
    }


    public void displayBoard(){
        System.out.println("------------Board------------");
        tiles.forEach((pos,tile)->{
            System.out.print("Tile at" + pos.toString() + " :"+ tile.getColor().toString()+"\n");

        });
        System.out.println("-----------------------------\n");
    }

}
