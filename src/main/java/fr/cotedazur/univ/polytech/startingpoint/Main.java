package fr.cotedazur.univ.polytech.startingpoint;

public class Main {


    public static String hello() {
        return "Hello World!";
    }

    public static void main(String... args) {

        Board board = new Board();
        Engine engine = new Engine(board);
        Robot robot1 = new Robot(engine);
        Robot robot2 = new Robot(engine);

        /** tour 1 **/

        Position p1 = robot1.placeTileAt(1,0,TileColor.GREEN);
        System.out.println("Robot 1 a posé une tuile en " +  p1);

        /** tour 2 **/
        Position p2 = robot2.placeTileAt(0,1,TileColor.PINK);
        System.out.println("Robot 2 a posé une tuile en " +  p2);

        /** Tour 3 : Robot 1 rejoue **/

        Position p3 = robot1.placeTileAt(2, 0, TileColor.YELLOW);
        System.out.println("Robot 1 a posé une tuile en " + p3);

        /** Tour 4 : Robot 2 rejoue **/

        Position p4 = robot2.placeTileAt(0, 2, TileColor.GREEN);
        System.out.println("Robot 2 a posé une tuile en " + p4);

        /** Afficher le nombre total de tuiles sur le plateau **/

        System.out.println("Nombre total de tuiles : " + board.getNumTiles());







    }

}
