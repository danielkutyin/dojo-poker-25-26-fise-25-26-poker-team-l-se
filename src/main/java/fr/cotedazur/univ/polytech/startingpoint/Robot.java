package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Random;

public class Robot {
    private Board board;

    public Robot( Board board) {
        this.board = board;
    }

    /** le robot donne l'ordre à l'engine pour placer la tuile aux coord choisies et récuperer la position **/



    public void placeTileAdEtang (){

        Board b = this.board;
        Position pond = this.board.getPond().getPosition();
        while(true) {
            Position p = pond.getNeighbourPosbyindex(new Random().nextInt(6));// position tuile aléatoire adjacente
            TileColor color = TileColor.values()[new Random().nextInt(TileColor.values().length-1) + 1];// couleur aléatoire
            try {
                b.addTile(new Tile(p, color), p);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
