package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Position {
    private int height;
    private int width;


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

    public Position getNeighbourPosbyindex(int index){
        Position direction=adjacentPos.get(index);
        return new Position( this.height+direction.height ,  this.width+direction.width );
    }

    public boolean isNeighbour(Position pos){
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
