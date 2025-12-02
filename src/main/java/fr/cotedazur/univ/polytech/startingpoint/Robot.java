package fr.cotedazur.univ.polytech.startingpoint;

public class Robot {
    private final Engine engine;

    public Robot(Engine engine){
        this.engine = engine;
    }

    /** le robott donne l'ordre à l'engine pour placer la tuile aux coord choisies et récuperer la position **/

    public Position placeTileAt (int height, int width, TileColor color){
       return engine.placeTile(height, width, color);
    }
}
