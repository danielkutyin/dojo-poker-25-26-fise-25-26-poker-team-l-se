package fr.cotedazur.univ.polytech.startingpoint;

public class Panda {
    private Position pos;

    public Panda() {
        this.pos = new Position(0,1);
    }

    public Position getPos() {
        return pos;
    }
    public void setPos(Position pos) {
        this.pos = pos;
    }
}
