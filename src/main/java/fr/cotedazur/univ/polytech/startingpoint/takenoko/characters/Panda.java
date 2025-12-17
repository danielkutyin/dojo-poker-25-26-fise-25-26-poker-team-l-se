package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;

public class Panda {
    private Position pos;

    public Panda() {
        this.pos = new Position(0,0);
    }

    public Position getPos() {
        return pos;
    }
    public void setPos(Position pos) {
        this.pos = pos;
    }
}
