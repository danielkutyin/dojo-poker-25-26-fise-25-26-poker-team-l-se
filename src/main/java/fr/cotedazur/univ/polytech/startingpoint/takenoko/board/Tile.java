package fr.cotedazur.univ.polytech.startingpoint.takenoko.board;


import fr.cotedazur.univ.polytech.startingpoint.takenoko.elements.Bambou;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tile {
    private Position pos;
    private final TileColor color;
    private List<Bambou> bambous = new ArrayList<>();


    public Tile(Position pos, TileColor color) {
        this.pos = pos;
        this.color = color;
        this.bambous = new ArrayList<>();
    }




    public Position getPosition() {
        return pos;
    }
    public TileColor getColor() {
        return color;
    }
    public List<Bambou> getBambous() {
        return bambous;
    }
    public void setPosition(Position position){
        pos = position;
    }

    public void addBambou() {

        if (color == TileColor.POND) {
            return;
        }
        if (bambous.size() < 4) {
            bambous.add(new Bambou(this.color));
        }
    }

    public boolean removeBambou(){
        if(color == TileColor.POND) {
            return false;
        }
        if (bambous.isEmpty()){
            return false;
        }
        bambous.removeLast();
        return true;
    }

    public int getNbBambous() {
        return bambous.size();
    }

    public void setNbBambous(int nbBambous) {
        this.bambous = new ArrayList<>();
        for (int i = 0; i < nbBambous; i++) {
            this.bambous.add(new Bambou(this.color));
        }
    }


    public boolean TileWithoutBambousOrPond(){
        if (color != TileColor.POND) {
            return getNbBambous()==0 ;
        }
        return true;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Tile tile)) return false;
        return Objects.equals(pos,tile.pos) && color==tile.color;
    }

    @Override
    public int hashCode(){
        return Objects.hash(pos);
    }




    @Override
    public String toString() {
        return "Tile{" + "pos=" + pos + ", color=" + color.name() + '}';
    }
}
