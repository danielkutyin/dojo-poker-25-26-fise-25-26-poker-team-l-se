package fr.cotedazur.univ.polytech.startingpoint.takenoko;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.RobotPanda;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.game.Engine;

public class Main {



    public static void main(String... args) throws Exception {


        Board board = new Board();
        Robot r1 = new Robot(board);
        Robot r2 = new RobotPanda(board);

        Robot[] robots={r1,r2};

        Engine engine= new Engine(board,robots);
        engine.runDemo();







    }

}
