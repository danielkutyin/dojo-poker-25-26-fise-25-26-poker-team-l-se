package fr.cotedazur.univ.polytech.startingpoint;

public class Position {
    private int height;
    private int width;

    public Position(int height, int width){
        this.height=height;
        this.width=width;
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
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
        return 31*width+height;
    }

    @Override
    public String toString() {
        return ("("+width+","+height+")");
    }
}
