package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position pos = (Position) o;
        return height == pos.height && width == pos.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

    @Override
    public String toString() {
        return ("("+ height +","+ width +")");
    }
}
