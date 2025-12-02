package fr.cotedazur.univ.polytech.startingpoint;

public class Robot {
    private final Engine engine;

    public Robot(Engine engine){
        this.engine = engine;
    }

    /** le robott donne l'ordre à l'engine pour placer la tuile aux coord choisies **/

    public void placeTileAt (int height, int width, TileColor color){
        engine.placeTile(height, width, color);
    }
}
