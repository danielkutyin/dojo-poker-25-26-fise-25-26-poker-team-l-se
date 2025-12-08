package fr.cotedazur.univ.polytech.startingpoint;

public enum Direction {
    BasDroite(0,1),
    BasGauche(-1,1),
    Gauche(-1,0),
    Droite(1,0),
    HautGauche(0,-1),
    HautDroite(1,-1);

    private int height;
    private int width;
    private Position pos;


    Direction(int height,int width){
        this.height = height;
        this.width = width;
    }
    public static Position move(Position pos, Direction direction){
        int posHeight=pos.getHeight()+direction.height;
        int posWidth=pos.getWidth()+direction.width;
        return new Position(posHeight,posWidth);
    }
    public static Position moveALot(Position pos, Direction direction,int number){
        for(int i =0;i<number;i++){
            pos=move(pos,direction);
        }
        return pos;
    }
}
