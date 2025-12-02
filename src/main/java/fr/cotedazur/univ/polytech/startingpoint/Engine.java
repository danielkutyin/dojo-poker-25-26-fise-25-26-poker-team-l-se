package fr.cotedazur.univ.polytech.startingpoint;

public class Engine {
    private final Board board;

    public Engine(Board board){
        this.board = board;
    }

    /** on place la tuile (couleur donné) aux coordonnées (height, width) **/

    /** cette fonction crée la position, la tuile puis elle appelle board.setTile **/
    public void placeTile(int height, int width,TileColor color){
        Position position = new Position(height, width);
        Tile tile = new Tile(position, color);
        board.setTile(tile,position);
    }



}
