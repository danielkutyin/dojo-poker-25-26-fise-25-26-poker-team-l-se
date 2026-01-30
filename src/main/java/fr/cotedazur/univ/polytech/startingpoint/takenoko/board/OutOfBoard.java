package fr.cotedazur.univ.polytech.startingpoint.takenoko.board;

public class OutOfBoard extends RuntimeException {
    public OutOfBoard() {
        super("not in the board");
    }
}
