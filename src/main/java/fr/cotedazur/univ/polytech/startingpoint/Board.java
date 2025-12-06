package fr.cotedazur.univ.polytech.startingpoint;

import java.util.*;

public class Board {

    private final Map<Position, Tile> tiles = new HashMap<>();
    private final Tile pond;
    private List<Position> neighbours = new ArrayList<>();

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
    public void setTile(Tile tile,Position position){
        tiles.put(position,tile);
    }
    public List<Position> listBon(List<Position> voisins){
        List<Position> voisinsVrai = new ArrayList<>();
        for (Position voisin : voisins) {
            if (!(tiles.containsKey(voisin))) voisinsVrai.add(voisin);
        }
        return voisinsVrai;
    }
    public List<Position> setNeighbours(Position pos){
        List<Position> neighbours = new ArrayList<>();
        neighbours.add(new Position(pos.getHeight() + 1, pos.getWidth() - 1)); // hautDroite
        neighbours.add(new Position(pos.getHeight(),     pos.getWidth() - 1)); // hautGauche
        neighbours.add(new Position(pos.getHeight() + 1, pos.getWidth()));     // droite
        neighbours.add(new Position(pos.getHeight() - 1, pos.getWidth()));     // gauche
        neighbours.add(new Position(pos.getHeight() - 1, pos.getWidth() + 1)); // basGauche
        neighbours.add(new Position(pos.getHeight(),     pos.getWidth() + 1)); // basDroite
        return neighbours;
    }
    public boolean verifRulesPond(Position pos){
        List<Position> vois = setNeighbours(pos);
        for (Position v : vois) {
            if (v.equals(pond.getPosition())) {
                return true;
            }
        }
        return false;
    }
    public boolean verifRule2Touch(Position pos){
        int count = 0;
        for (Position n : setNeighbours(pos)) {
            if (tiles.containsKey(n)) count++;
        }
        return count >= 2;
    }
    public void addTile(Tile tile){
//        boolean isadj=false;
//        for (Position p : tiles.keySet()) {
//
//            if (p.isNeighbour(pos)) {
//
//                isadj=true;
//                break;
//            }
//        }
        Position pos = tile.getPosition();
        if (tiles.containsKey(pos)) {
            throw new ArgumentalreadyExistOrnotAdj("Tile already exists at " + pos);
        }
        if (verifRulesPond(pos)) {
            tiles.put(pos, tile);
            return;
        }

        if (verifRule2Touch(pos)) {
            tiles.put(pos, tile);
            return;
        }

        throw new ArgumentalreadyExistOrnotAdj("this tile " + pos.toString() + " cannot be added");
//        if(!isadj){
//            throw new ArgumentalreadyExistOrnotAdj("this tile " + pos.toString() + "can not be added");
//        }
    }


    public void displayBoard(){
        System.out.println("------------Board------------");
        tiles.forEach((pos,tile)->{
            System.out.print("Tile at" + pos.toString() + " :"+ tile.getColor().toString()+"\n");

        });
        System.out.println("-----------------------------\n");
    }


}
