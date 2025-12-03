package fr.cotedazur.univ.polytech.startingpoint;

public class Main {



    public static void main(String... args) {


        Board board = new Board();
        board.displayBoard();
        Robot[] robots = {new Robot(board),new Robot(board)};
        int turn = 0;
        while(board.getNumTiles()<7){
         robots[turn].placeTileAdEtang();
         board.displayBoard();
         turn=(turn+1)%robots.length;
        }








    }

}
