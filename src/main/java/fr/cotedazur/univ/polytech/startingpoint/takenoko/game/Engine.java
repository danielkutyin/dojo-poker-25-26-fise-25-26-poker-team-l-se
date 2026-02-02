package fr.cotedazur.univ.polytech.startingpoint.takenoko.game;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.exceptions.ArgumentalreadyExistOrnotAdj;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesDeck;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesPanda;

import java.util.Random;

public class Engine {
    private final Board board;
    private final Robot[] robots;
    private final ObjectivesPanda pandaDeck;
    private final ObjectivesDeck gardenerDeck;

    public Engine(Board board, Robot[] robots) {
        this.board = board;
        this.robots = robots;
        this.pandaDeck = new ObjectivesPanda(new Random());
        this.gardenerDeck = new ObjectivesDeck(new Random());
    }
    public ObjectivesPanda getPandaDeck() { return pandaDeck; }
    public ObjectivesDeck getGardenerDeck() { return gardenerDeck; }


    public Board getBoard() {
        return board;
    }
    public Robot[] getRobots() {
        return robots;
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

    public void runDemo() throws Exception {
        System.out.println("--- Debut de la partie ---");
        board.displayBoard();
        int turn=0;
        int turnNumber=1;
        for (Robot r : robots) {
            r.drawPandaObjective(pandaDeck);
            r.drawGardenerObjective(gardenerDeck);
        }

        while(board.getNumTiles()<14){
            Robot robot = robots[turn];
            System.out.println("--- Tour "+turnNumber+" ---");
            robot.playTurn(pandaDeck,gardenerDeck);
            turn=(turn+1)%robots.length;
            turnNumber++;
        }

        System.out.println("--- Fin de la partie ---");

        System.out.println("plateau ");
        board.displayBoard();

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



    }


}
