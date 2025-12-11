package fr.cotedazur.univ.polytech.startingpoint.takenoko.board;


import fr.cotedazur.univ.polytech.startingpoint.takenoko.elements.Bambou;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final Position pos;
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

    public void addBambou() {

        if (color == TileColor.POND) {
            return;
        }
        if (bambous.size() < 4) {
            bambous.add(new Bambou(this.color));
        }
    }

    public int getNbBambous() {
        return bambous.size();
    }



    @Override
    public String toString() {
        return "Tile{" + "pos=" + pos + ", color=" + color.name() + '}';
    }
}
