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
        if (playable.isEmpty()) {
            System.out.println(name + " n'a aucune position jouable.");
            return;
        }
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

  /* public Position playPandaMove(){
        int range =  new Random().nextInt(6)+1;//pour ne pas choisir 0
        Panda po = this.board.getPanda();
        List<Direction> directionsAvailable = new ArrayList<>();
        for(int i =0;i<6;i++){
            if(Direction.rangeMovement(po.getPos(),Direction.values()[i]) >0) directionsAvailable.add(Direction.values()[i]);
        }
        int i = new Random().nextInt(directionsAvailable.size());
        Direction moved = directionsAvailable.get(i);
        int randMove = new Random().nextInt(Direction.rangeMovement(po.getPos(),directionsAvailable.get(i)));
        Position placed = moved.move(po.getPos(),moved,randMove);
        if (board.isExistInTiles(placed)) {
            po.setPos(placed);
            return placed;
        }
        else{
            throw new ArgumentalreadyExistOrnotAdj("Tile Does not exists " + placed);
        }
    }*/
    public Position playPandaMove() {
        Panda po = this.board.getPanda();
        Position start = po.getPos();

        List<Direction> directionsAvailable = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if (Direction.rangeMovement(start, dir) > 0) directionsAvailable.add(dir);
        }

        if (directionsAvailable.isEmpty()) {
            throw new ArgumentalreadyExistOrnotAdj("No valid direction for Panda from " + start);
        }

        Direction moved = directionsAvailable.get(random.nextInt(directionsAvailable.size()));
        int maxRange = Direction.rangeMovement(start, moved);
        int dist = random.nextInt(maxRange) + 1; // distance minimale = 1

        Position placed = Direction.move(start, moved, dist);

        if (board.isExistInTiles(placed)) {
            po.setPos(placed);
            return placed;
        } else {
            throw new ArgumentalreadyExistOrnotAdj("Tile Does not exist " + placed);
        }
    }


    public void playPandaTurn(){
        System.out.println("Robot " + name + " chooses to move panda.");
        while (true){
            try{
                Position place =playPandaMove();
                System.out.println("panda moves to " + place.toString());
                boolean ate = board.tryEatBambouOnPandaTile();
                if (ate) {
                    addScore(1);
                    System.out.println("Robot " + name + " : Panda a mangé 1 bambou. Score = " + score);
                } else {
                    System.out.println("Robot " + name + " : Rien à manger. Score = " + score);
                }
                break;
            }
            catch(ArgumentalreadyExistOrnotAdj e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void playTurn(){
        int choice = random.nextInt(3);
        if((choice==0)||(board.getNumTiles()<3)){ /*ne bouger pas le jardinier ou Panda  avant 3 tuiles*/
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

        if (board.isExistInTiles(placed)) {
            g.setPos(placed);
            System.out.println("Gardener moves from " + start  + " dir=" + moved + " dist=" + dist + " to " + placed);
            return placed;
        } else {
            throw new ArgumentalreadyExistOrnotAdj("Tile does not exist " + placed);
        }


    }

    public void playGardenerTurn() {
        System.out.println("Robot " + name + " choisit de déplacer le jardinier.");
        while (true) {
            try {
                Position p = playGardenerMove();
                System.out.println("Jardinier déplacé en " + p);
                board.plantBambooOnGardenerTile();
                if (!(board.getTileAt(p).getColor().equals(TileColor.POND))) {
                    System.out.println("Un bambou pousse sur la tuile " + board.getGardener().getPos()
                            + " (total = " + board.getTileAt(p).getNbBambous() + ")");
                }
                break;
            }
            catch (ArgumentalreadyExistOrnotAdj e) {
                System.out.println("Déplacement jardinier impossible : " + e.getMessage());
            }
        }
    }



}

