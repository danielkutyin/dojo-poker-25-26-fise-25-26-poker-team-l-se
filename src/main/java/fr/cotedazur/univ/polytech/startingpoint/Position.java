package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private int q;
    private int r;

    public Position(int height, int width){
        this.q =height;
        this.r =width;
    }
    public double getQ(){
        return q;
    }
    public double getR(){
        return r;
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
    public Position getNeighbourPosbyindex(int index){
        Position direction=adjacentPos.get(index);
        return new Position( this.q+direction.q ,  this.r+direction.r );
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
            if(this.q == position.q && this.r == position.r){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31* r + q;
    }

    @Override
    public String toString() {
        return ("("+ q +","+ r +")");
    }
}
