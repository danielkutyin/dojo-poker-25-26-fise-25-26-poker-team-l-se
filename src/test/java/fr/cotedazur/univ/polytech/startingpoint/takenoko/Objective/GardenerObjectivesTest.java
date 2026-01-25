// Java
package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Improvements;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.PandaObjective;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GardenerObjectivesTest {

    @Test
    public void testGardenerObjectiveIsCompleted_withEnoughBamboos() {
        Board board = new Board();



        Tile green1 = new Tile(new Position(0,1), TileColor.GREEN);   // OK, voisin de (0,0)
        Tile green2 = new Tile(new Position(1,0), TileColor.GREEN);   // OK, autre voisin

        board.addTile(green1); // passe
        board.addTile(green2); // passe

        Position pos = new Position(1, 1);
        Tile tile = new Tile(pos, TileColor.GREEN);
        board.addTile(tile);

        // Planter 4 bambous sur la tuile
        for (int i = 0; i < 4; i++) {
            board.getGardener().setPos(pos);
            board.plantBambooOnGardenerTile();
        }

        GardnerObjectives objective =
                new GardnerObjectives(4, 1, TileColor.GREEN, 4, Improvements.NONE);

        assertTrue(objective.isAchieved(board));
    }



}
