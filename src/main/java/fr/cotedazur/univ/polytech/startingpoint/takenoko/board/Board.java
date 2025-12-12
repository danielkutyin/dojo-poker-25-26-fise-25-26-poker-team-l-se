package fr.cotedazur.univ.polytech.startingpoint.takenoko.board;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Gardener;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Panda;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.exceptions.ArgumentalreadyExistOrnotAdj;

import java.util.*;

public class Board {

    private static final Map<Position, Tile> tiles = new HashMap<>();
    private final Tile pond;
    private Panda panda;
    private List<Position> neighbours = new ArrayList<>();
    private final Gardener gardener;

    public Board() {
        if (tiles.isEmpty()) {
            resetBoard();
        }
        this.pond=new Tile(new Position(0,0), TileColor.POND);
        tiles.put(pond.getPosition(),pond);
        this.panda =new Panda();
        this.gardener = new Gardener();
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
    public static Map<Position, Tile> getHashmap(){
        return tiles;
    }
    public List<Position> setNeighbours(Position pos){
        List<Position> neighbours = new ArrayList<>();
        neighbours.add(new Position(pos.getHeight() + 1, pos.getWidth() - 1));//HautDroite
        neighbours.add(new Position(pos.getHeight(),     pos.getWidth() - 1));//HautGauche
        neighbours.add(new Position(pos.getHeight() + 1, pos.getWidth()));//Droite
        neighbours.add(new Position(pos.getHeight() - 1, pos.getWidth()));//Gauche
        neighbours.add(new Position(pos.getHeight() - 1, pos.getWidth() + 1));//BasGauche
        neighbours.add(new Position(pos.getHeight(),     pos.getWidth() + 1));//BasDroite
        return neighbours;
    }
    public boolean verifRulesPond(Position pos){
        List<Position> vois = setNeighbours(pos);
        for (Position v : vois) {
            if (v.equals(pond.getPosition())) {//Regarde si dans la liste des voisins de pos il y a le pond
                return true;
            }
        }
        return false;
    }
    public boolean verifRule2Touch(Position pos){
        int count = 0;
        for (Position n : setNeighbours(pos)) { // compte le nombre de voisins de pos et si il y en a plus de 2 alors on verifie la regle
            if (tiles.containsKey(n)) count++;
        }
        return count >= 2;
    }

    public List<Position> getPlayablePositions(){
        List<Position> playablePositions = new ArrayList<>();
        for(Position existing : tiles.keySet()){
            List<Position> voisins = setNeighbours(existing);
            for (Position voisin : voisins) {
                if(tiles.containsKey(voisin)) continue;
                if(verifRulesPond(voisin) || verifRule2Touch(voisin)){
                    if(!playablePositions.contains(voisin)){
                        playablePositions.add(voisin);
                    }
                }

            }
        }
        return playablePositions;
    }

    public void addTile(Tile tile){
        Position pos = tile.getPosition();
        //si il y existe déjà la tuile on ne peut pas la poser
        if (tiles.containsKey(pos)) {
            throw new ArgumentalreadyExistOrnotAdj("Tile already exists at " + pos);
        }
        //on regarde la premiere regle (POND)
        if (verifRulesPond(pos)) {
            tiles.put(pos, tile);
            return;
        }
        //sinon on  regarde si la deuxieme est verifié
        if (verifRule2Touch(pos)) {
            tiles.put(pos, tile);
            return;
        }
        //sinon exception
        throw new ArgumentalreadyExistOrnotAdj("this tile " + pos.toString() + " cannot be added");
    }
    public Panda getPanda(){
        return this.panda;
    }

    public Gardener getGardener() {
        return gardener;
    }

    public void plantBambooOnGardenerTile() {
        Tile tile = tiles.get(gardener.getPos());
        if (tile != null) {
            tile.addBambou();
            System.out.println("Un bambou pousse sur la tuile " + gardener.getPos()
                    + " (total = " + tile.getNbBambous() + ")");
        }
    }


    public void displayBoard(){
        System.out.println("------------Board------------");
        tiles.forEach((pos,tile)->{
            System.out.print("Tile at" + pos.toString() + " :"+ tile.getColor().toString()+"\n");

        });
        System.out.println("-----------------------------\n");
    }

    public static void resetBoard() {
        tiles.clear();
        Tile pond = new Tile(new Position(0, 0), TileColor.POND);
        tiles.put(pond.getPosition(), pond);
    }


}
