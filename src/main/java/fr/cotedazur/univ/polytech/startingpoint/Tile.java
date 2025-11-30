package fr.cotedazur.univ.polytech.startingpoint;

public class Tile {
    private final Position pos;
    private final TileColor color;


    public Tile(Position pos, TileColor color) {
        this.pos = pos;
        this.color = color;
    }

    public Position getPosition() {
        return pos;
    }
    public TileColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Tile{" + "pos=" + pos + ", color=" + color + '}';
    }
}
