package fr.cotedazur.univ.polytech.startingpoint;

public class Main {



    public static void main(String... args) {


        Board board = new Board();
        Robot r1 = new Robot(board);
        Robot r2 = new Robot(board);

        Robot[] robots={r1,r2};

        Engine engine= new Engine(board,robots);
        engine.runDemo();







    }

}
