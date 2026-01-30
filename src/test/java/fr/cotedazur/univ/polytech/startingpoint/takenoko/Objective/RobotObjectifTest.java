package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.PandaObjective;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RobotObjectifTest {


    @Test
    public void testGardenerObjectiveIsCompleted_withInsufficientBamboos() throws Exception {
        Board board = new Board();
        Position pos = new Position(0, 1);
        Tile tile = new Tile(pos, TileColor.GREEN);
        board.addTile(tile);

        // Planter seulement 2 bambous
        for (int i = 0; i < 2; i++) {
            board.getGardener().setPos(pos);
            board.plantBambooOnGardenerTile();
        }

        GardnerObjectives objective =
                new GardnerObjectives(4, 1, TileColor.GREEN, 4, Improvements.NONE);

        assertFalse(objective.isAchieved(board));
    }
    @Test
    void twoSamePandaObjectives_onlyOneValidated_ifReserveEnoughForOne() {
        Board board = new Board();
        Robot robot = new Robot(board);

        Map<TileColor, Integer> req = new EnumMap<>(TileColor.class);
        req.put(TileColor.GREEN, 2);

        PandaObjective obj1 = new PandaObjective(req, 3);
        PandaObjective obj2 = new PandaObjective(req, 3);

        robot.addPandaObjectiveForTest(obj1);
        robot.addPandaObjectiveForTest(obj2);
        robot.setEatenForTest(TileColor.GREEN, 2);

        robot.checkPandaObjectives();

        assertEquals(3, robot.getScore());
        assertEquals(0, robot.getEaten(TileColor.GREEN));
        assertEquals(1, robot.getPandaObjectives().size()); // getter unmodifiable OK pour size()
    }
    @Test
    void checkPandaObjectives_calledTwice_doesNotGivePointsTwice() {
        Board board = new Board();
        Robot robot = new Robot(board);

        Map<TileColor, Integer> req = new EnumMap<>(TileColor.class);
        req.put(TileColor.GREEN, 2);
        PandaObjective obj = new PandaObjective(req, 3);

        robot.addPandaObjectiveForTest(obj);
        robot.setEatenForTest(TileColor.GREEN, 2);

        // 1ère fois : valide
        robot.checkPandaObjectives();
        int scoreAfterFirst = robot.getScore();
        int greenAfterFirst = robot.getEaten(TileColor.GREEN);
        int handSizeAfterFirst = robot.getPandaObjectives().size();

        // 2ème fois : doit rien changer
        robot.checkPandaObjectives();

        assertEquals(scoreAfterFirst, robot.getScore());
        assertEquals(greenAfterFirst, robot.getEaten(TileColor.GREEN));
        assertEquals(handSizeAfterFirst, robot.getPandaObjectives().size());

        // Bonus : valeurs attendues exactes
        assertEquals(3, scoreAfterFirst);
        assertEquals(0, greenAfterFirst);
        assertEquals(0, handSizeAfterFirst);
    }
}
