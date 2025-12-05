package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Random;

public class Robot {

    private static int nextId = 1;
    private Board board;
    private final Random random = new Random();
    private final String name;
    private int score=0;

    public Robot( Board board) {
        this.board = board;
        this.name="Robot"+(nextId++);
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
    /** le robot donne l'ordre à l'engine pour placer la tuile aux coord choisies et récuperer la position **/



    public void placeTileAdEtang (){

        Position pond = this.board.getPond().getPosition();
        Position[] freePositions = new Position[Position.adjacentPos.size()];
        int freeCount=0;
        //collecte les positions libres a cote de l etang
        for(int i=0;i<Position.adjacentPos.size();i++){
            Position candidate=pond.getNeighbourPosbyindex(i);
            if(board.getTileAt(candidate)==null){
                freePositions[freeCount++]=candidate;
            }
        }

        //plus de places disponibles
        if(freeCount==0){
            System.out.println("Plus de places autour de l etang");
            return;
        }

        Position chosen=freePositions[random.nextInt(freeCount)];

        TileColor[] values=TileColor.values();
        int colorIndex=random.nextInt(values.length-1);
        TileColor color=values[colorIndex];

        board.addTile(new Tile(chosen,color));
        addScore(1);
        System.out.println(name + " pose une tuile "+ color +" en "+ chosen + " | score = "+score);
    }
}
