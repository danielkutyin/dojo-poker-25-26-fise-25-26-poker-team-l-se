package fr.cotedazur.univ.polytech.startingpoint.takenoko.elements;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

public class Bambou {

    private final TileColor color;

    public Bambou(TileColor color) {
        this.color = color;
    }

    public TileColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "B(" + color + ")";
    }
}
