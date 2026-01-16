package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class GardnerObjectivesTest {

    @Test
    void isAchievedTest() {
        Board board = new Board();

        Tile green1 = new Tile(new Position(0,1),TileColor.GREEN);
        green1.setNbBambous(3);

        Tile green2 = new Tile(new Position(1,0),TileColor.GREEN);
        green2.setNbBambous(4);

        Tile pink = new Tile(new Position(1,1),TileColor.PINK);
        pink.setNbBambous(5);

        board.addTile(green1);
        board.addTile(green2);
        board.addTile(pink);

        GardnerObjectives objective =
                new GardnerObjectives(3, 2, TileColor.GREEN , 4, Improvements.NONE);

        assertFalse(objective.isAchieved(board));
    }

    @Test
    void isAchieved_version2() {
        Board board = new Board();

        Tile green1 = new Tile(new Position(0,-1),TileColor.GREEN);
        green1.setNbBambous(2); // pas assez

        Tile green2 = new Tile(new Position(-1,0),TileColor.GREEN);
        green2.setNbBambous(3); // assez

        board.addTile(green1);
        board.addTile(green2);

        GardnerObjectives objective =
                new GardnerObjectives(3, 2, TileColor.GREEN , 4 , Improvements.NONE);

        assertFalse(objective.isAchieved(board));
    }
}
