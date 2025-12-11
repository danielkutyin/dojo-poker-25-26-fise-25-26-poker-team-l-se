package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;

public class Gardener {

    private Position pos;

    public Gardener() {

        this.pos = new Position(0, 0);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Gardener at " + pos;
    }
}