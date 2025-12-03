package fr.cotedazur.univ.polytech.startingpoint;

public class Main {


    public static String hello() {
        return "Hello World!";
    }

    public static void main(String... args) {

        Board board = new Board();
        Robot robot1 = new Robot(board);
        Robot robot2 = new Robot(board);
        while(board.getNumTiles()!=6 ) {
            robot1.placeTileAdEtang();
            robot2.placeTileAdEtang();
        }








    }

}
