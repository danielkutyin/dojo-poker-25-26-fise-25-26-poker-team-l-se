package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Random;

public class Engine {

    private final Board board;
    private final Robot[] robots;
    public Engine(Board board, Robot[] robots) {
        this.board = board;
        this.robots = robots;
    }

    public Board getBoard() {
        return board;
    }
    public Robot[] getRobots() {
        return robots;
    }

    public void playOneTurn(int index){
        Robot robot = robots[index];
        System.out.println("---Tour de "+robot.getName()+"---");
        robot.placeTileAdEtang();
        board.displayBoard();
    }

    public void playPandaTurn(int index){
        Robot robot = robots[index];
        while (true){
            try{
            Position place =robot.playPandaMove();
            System.out.println("panda moves to " + place.toString());
                break;
            }
            catch(ArgumentalreadyExistOrnotAdj e){
                System.out.println(e.getMessage());
            }
        }

    }

    public Robot getWinner(){
        Robot best = robots[0];
        for(int i=0;i<robots.length;i++){
            if(robots[i].getScore()>best.getScore()){
                best=robots[i];
            }
        }
        return best;
    }

    public void runDemo(){
        System.out.println("--- Debut de la partie ---");
        board.displayBoard();
        int turn=0;
        int turnNumber=1;

        while(board.getNumTiles()<14){
            System.out.println("--- Tour "+turnNumber+" ---");
            playOneTurn(turn);
            turn=(turn+1)%robots.length;
            turnNumber++;
        }

        System.out.println("--- Fin de la partie ---");

        for(Robot robot:robots) {
            System.out.println("--- " + robot.getName() + " : " + robot.getScore() + " points");
        }
        Robot r1 = robots[0];
        Robot r2 = robots[1];
        if (r1.getScore() == r2.getScore()){
            System.out.println("egalité entre "+ r1.getName()+" et "+ r2.getName()+" avec "+ r1.getScore() + " points");
        }
        else {
            Robot winner = getWinner();
            System.out.println("--- Gagnant : " + winner.getName() + " avec " + winner.getScore() + " points");
        }
        playPandaTurn(turn);


    }


}
