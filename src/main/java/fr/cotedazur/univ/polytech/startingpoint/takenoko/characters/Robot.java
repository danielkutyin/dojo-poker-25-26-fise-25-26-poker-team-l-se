package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.exceptions.ArgumentalreadyExistOrnotAdj;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot {

    private static int nextId = 1;
    private Board board;
    private final Random random = new Random();
    private final String name;
    private int score = 0;

    public Robot(Board board) {
        this.board = board;
        this.name = "Robot" + (nextId++);
    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int delta) {
        this.score += delta;
    }

    /**
     * le robot donne l'ordre à l'engine pour placer la tuile aux coord choisies et récuperer la position
     **/


    public void playTileTurn() {

        List<Position> playable = board.getPlayablePositions();

        Position chosen = playable.get(random.nextInt(playable.size()));
        Tile tile = new Tile(chosen, TileColor.GREEN);
        board.addTile(tile);
        addScore(1);
        System.out.println(name + "placer tuile à " + chosen + " score =" + score);

/*        Position pondPos = board.getPond().getPosition();
        List<Position> voisins = board.setNeighbours(pondPos);
        List<Position> libres = board.listBon(voisins);

        if (libres.isEmpty()) {
            System.out.println("Full");
            return;
        }
        Position chosen = libres.get(random.nextInt(libres.size()));
        board.addTile(new Tile(chosen, TileColor.GREEN));
        addScore(1);
        System.out.println(name + "place tile at" + chosen + "score =" + score);
    }*/
//    public void placeTileAdEtang (){
//
//        Position pond = this.board.getPond().getPosition();
//        //Position[] freePositions = new Position[Position.adjacentPos.size()];
//        //int freeCount=0;
//        //collecte les positions libres a cote de l etang
//
//        /*for(int i=0;i<Position.adjacentPos.size();i++){
//            Position candidate=pond.getNeighbourPosbyindex(i);
//            if(board.getTileAt(candidate)==null){
//                freePositions[freeCount++]=candidate;
//            }
//        }
//
//        //plus de places disponibles
//        if(freeCount==0){
//            System.out.println("Plus de places autour de l etang");
//            return;
//        }
//
//        Position chosen=freePositions[random.nextInt(freeCount)];
//
//        TileColor[] values=TileColor.values();
//        int colorIndex=random.nextInt(values.length-1);
//        TileColor color=values[colorIndex];
//*/
//        Position chosen=new Position(random.nextInt(3),random.nextInt(3));
//        board.addTile(new Tile(chosen,TileColor.GREEN));
//        addScore(1);
//        System.out.println(name + " pose une tuile  en "+ chosen + " | score = "+score);
  }

    public Position playPandaMove(){
        int range =  new Random().nextInt(6)+1;//pour ne pas choisir 0
        Panda po = this.board.getPanda();
        int i = new Random().nextInt(6);
        Direction moved = Direction.values()[i];
        Position placed = moved.move(po.getPos(),moved,i);
        if (this.board.getHashmap().containsKey(placed)){
            po.setPos(placed);
            return placed;
        }
        else{
            throw new ArgumentalreadyExistOrnotAdj("Tile Does not exists " + placed);
        }
    }

    public void playPandaTurn(){
        System.out.println("Robot " + name + " chooses to move panda.");
        while (true){
            try{
                Position place =playPandaMove();
                System.out.println("panda moves to " + place.toString());
                break;
            }
            catch(ArgumentalreadyExistOrnotAdj e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void playTurn(){
        int choice = random.nextInt(2);
        if(choice==0){
            playTileTurn();
        } else if (choice == 1) {
            playPandaTurn();
        } else {
            playGardenerTurn();
        }

    }

    public Position playGardenerMove() {
        Gardener g = this.board.getGardener();
        Position start = g.getPos();

        /** Chercher les directions où un mouvement est possible **/

        List<Direction> directionsAvailable = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if (Direction.rangeMovement(start, dir) > 0) {
                directionsAvailable.add(dir);
            }
        }


        if (directionsAvailable.isEmpty()) {
            throw new ArgumentalreadyExistOrnotAdj("No valid direction for Gardener from " + start);
        }

        /**  Choisir une direction valide **/

        Direction moved = directionsAvailable.get(new Random().nextInt(directionsAvailable.size()));

        /**  Choisir une distance valide **/

        int maxRange = Direction.rangeMovement(start, moved);
        int dist = new Random().nextInt(maxRange) + 1;

        /**  Calculer la position finale **/

        Position placed = Direction.move(start, moved, dist);

        /** Vérifier la tuile existe **/

        if (board.getHashmap().containsKey(placed)) {
            g.setPos(placed);
            System.out.println("Gardener moves from " + start  + " dir=" + moved + " dist=" + dist + " to " + placed);
            return placed;
        } else {
            throw new ArgumentalreadyExistOrnotAdj("Tile does not exist " + placed);
        }


    }

    public void playGardenerTurn() {
        System.out.println("Robot " + name + " choisit de déplacer le jardinier.");
        try {
            Position p = playGardenerMove();
            System.out.println("Jardinier déplacé en " + p);
            board.plantBambooOnGardenerTile();
        } catch (ArgumentalreadyExistOrnotAdj e) {
            System.out.println("Déplacement jardinier impossible : " + e.getMessage());
        }
    }



}

