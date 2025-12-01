package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private int height;
    private int width;
    private List<Position> neighbours = new ArrayList<>();
    private Board board;


    public Position(int height, int width){
        this.height =height;
        this.width =width;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    //List de positions adjacentes pour l'etang
    public static final List<Position> adjacentPos=List.of(
            new Position(1, 0), // droite
            new Position(1, -1), // haut à droite
            new Position(0, -1), // haut à gauche
            new Position(-1, 0), // gauche
            new Position(-1, 1), //bas à gauche
            new Position(0, 1) // bas à droite
    );

    public List<Position> setNeighbours(){
        Position hautDroite = new Position(height+3, width+1);
        Position hautGauche = new Position(height+3, width-1);
        Position droite = new Position(height, width+2);
        Position gauche = new Position(height, width-2);
        Position basGauche = new Position(height-3, width-1);
        Position basDroite = new Position(height-3, width+1);

        neighbours.add(hautDroite);
        neighbours.add(hautGauche);
        neighbours.add(droite);
        neighbours.add(gauche);
        neighbours.add(basDroite);
        neighbours.add(basGauche);

        return verifPositionAvailable(neighbours);
    }
    public List<Position> verifPositionAvailable(List<Position> list){
        for(int i=0;i<list.size();i++) {
            if (Board.getHashMap().containsKey(list.get(i))) {
                list.remove(list.get(i));
            }
        }
        return list;
    }
    public Position getNeighbourPosbyindex(int index){
        Position direction=adjacentPos.get(index);
        return new Position( this.height+direction.height ,  this.width+direction.width );
    }

    public Boolean isNeighbour(Position pos){
        for(int i=0;i<adjacentPos.size();i++){
            if(getNeighbourPosbyindex(i).equals(pos)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(obj instanceof Position){
            Position position = (Position)obj;
            if(this.height == position.height && this.width == position.width){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31* width + height;
    }

    @Override
    public String toString() {
        return ("("+ height +","+ width +")");
    }
}
