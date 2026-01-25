package fr.cotedazur.univ.polytech.startingpoint.takenoko.Strategy;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.Actions;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.SmartPandaStrategy;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Robot;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.PandaObjective;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SmartPandaStrategyTest {
    @Test
    void choose_before3Tiles_returnsTiles() {
        Board board = new Board();              // numTiles = 1 (pond)
        Robot robot = new Robot(board);
        SmartPandaStrategy strat = new SmartPandaStrategy();

        assertEquals(Actions.Tiles, strat.choose(new TurnView(board, robot)));
    }

    @Test
    void choose_whenUsefulBambooReachable_returnsPanda_andDestination() {
        Board board = new Board();
        Robot robot = new Robot(board);

        // Ajoute 2 tuiles pour arriver à 3 tuiles (pond incluse)
        board.addTile(new Tile(new Position(0, 1), TileColor.GREEN));
        board.addTile(new Tile(new Position(0, -1), TileColor.YELLOW));

        // Met des bambous : GREEN=1 sur (0,1), YELLOW=3 sur (0,-1)
        addBamboo(board, new Position(0, 1), 1);
        addBamboo(board, new Position(0, -1), 3);

        // Objectif : il manque du GREEN (GREEN=1)
        Map<TileColor, Integer> req = new EnumMap<>(TileColor.class);
        req.put(TileColor.GREEN, 1);
        PandaObjective obj = new PandaObjective(req, 3);

        // helpers "for test" (package-private) que tu as ajoutés
        robot.addPandaObjectiveForTest(obj);
        robot.setEatenForTest(TileColor.GREEN, 0);

        SmartPandaStrategy strat = new SmartPandaStrategy();
        TurnView view = new TurnView(board, robot);

        assertEquals(Actions.Panda, strat.choose(view));

        // Et la destination doit être la tuile verte (utile) même si l'autre a + de bambous
        assertTrue(strat.choosePandaDestination(view).isPresent());
        assertEquals(new Position(0, 1), strat.choosePandaDestination(view).get());
    }
    @Test
    void choosePandaDestination_whenNoBamboo_returnsEmpty() {
        Board board = new Board();
        Robot robot = new Robot(board);

        board.addTile(new Tile(new Position(0, 1), TileColor.GREEN));
        board.addTile(new Tile(new Position(0, -1), TileColor.YELLOW));
        // pas de bambou posé

        Map<TileColor, Integer> req = new EnumMap<>(TileColor.class);
        req.put(TileColor.GREEN, 1);
        robot.addPandaObjectiveForTest(new PandaObjective(req, 3));

        SmartPandaStrategy strat = new SmartPandaStrategy();
        TurnView view = new TurnView(board, robot);

        assertTrue(strat.choosePandaDestination(view).isEmpty());
        // et donc strat.choose(view) ne doit PAS renvoyer Panda
        assertNotEquals(Actions.Panda, strat.choose(view));
    }
    @Test
    void choosePandaDestination_twoUsefulTiles_picksMostBamboo() {
        Board board = new Board();
        Robot robot = new Robot(board);

        // 3 tuiles mini (pond + 2)
        Position greenPos = new Position(0, 1);
        Position yellowPos = new Position(0, -1);
        board.addTile(new Tile(greenPos, TileColor.GREEN));
        board.addTile(new Tile(yellowPos, TileColor.YELLOW));

        // Bambous : GREEN=1, YELLOW=3
        addBamboo(board, greenPos, 1);
        addBamboo(board, yellowPos, 3);

        // Objectif : il manque GREEN=1 et YELLOW=1 -> les deux tuiles sont "utiles"
        Map<TileColor, Integer> req = new EnumMap<>(TileColor.class);
        req.put(TileColor.GREEN, 1);
        req.put(TileColor.YELLOW, 1);
        robot.addPandaObjectiveForTest(new PandaObjective(req, 6));
        robot.setEatenForTest(TileColor.GREEN, 0);
        robot.setEatenForTest(TileColor.YELLOW, 0);

        SmartPandaStrategy strat = new SmartPandaStrategy();
        TurnView view = new TurnView(board, robot);

        assertTrue(strat.choosePandaDestination(view).isPresent());
        assertEquals(yellowPos, strat.choosePandaDestination(view).get(),
                "Les deux sont utiles, donc on choisit celle avec le plus de bambous (YELLOW=3)");
    }

    private static void addBamboo(Board board, Position p, int n) {
        Tile t = board.getTileAt(p);
        for (int i = 0; i < n; i++) t.addBambou();
    }
}

