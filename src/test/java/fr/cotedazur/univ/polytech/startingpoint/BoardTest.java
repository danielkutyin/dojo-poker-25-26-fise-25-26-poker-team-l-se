package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    @Test
    public void testBoardStartsWithPond(){
        Board board = new Board();
        //vérifier le nombre de tuiles placés doit etre a 1 lors de l initialisation
        assertEquals(1,board.getNumTiles());
        Tile pond=board.getPond();
        //verifier le renvoi de la couleur et et position de l etang
        assertEquals(TileColor.POND,pond.getColor());
        assertEquals(new Position(0,0),pond.getPosition());
    }
}
