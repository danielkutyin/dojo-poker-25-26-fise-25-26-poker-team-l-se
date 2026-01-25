package fr.cotedazur.univ.polytech.startingpoint.takenoko.Objective;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileObjectiveCard;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TileObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.TilePatternType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TileObjectivesTest {
    private TileObjectives checker;

    @BeforeEach
    void setup() {
        checker = new TileObjectives();
    }

    private void addGreen(Board board, Position p) {
        board.addTile(new Tile(p, TileColor.GREEN));
    }

    @Test
    void doubleGreen() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.DOUBLE, TileColor.GREEN, 2);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void doubleGreenRotation() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(-1, 1));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.DOUBLE, TileColor.GREEN, 2);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void triangleGreen() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, 1));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.TRIANGLE, TileColor.GREEN, 3);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void TriangleGreenMissing() {
        Board board = new Board();

        // seulement 2 tuiles -> pas triangle
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.TRIANGLE, TileColor.GREEN, 3);

        assertFalse(checker.isCompleted(board, card));
    }

    @Test
    void triangleGreen_false_ifRotatedTriangleHasWrongColor() {
        Board board = new Board();

        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0)); // wrong
        board.addTile(new Tile(new Position(1, 1), TileColor.YELLOW));
        addGreen(board, new Position(0, 2));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.TRIANGLE, TileColor.GREEN, 3);
        assertFalse(checker.isCompleted(board, card));
    }

    @Test
    void SquareInRotation() {
        Board board = new Board();

        addGreen(board, new Position(0, 1)); // adj pond
        addGreen(board, new Position(1, 0)); // adj pond (support so (1,1) legal)
        addGreen(board, new Position(1, 1)); // touches (0,1)+(1,0)
        addGreen(board, new Position(0, 2));
        addGreen(board, new Position(1, 2));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SQUARE, TileColor.GREEN, 4);
        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void squareGreen() {
        Board board = new Board();

        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, 1));
        addGreen(board, new Position(1, -1));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SQUARE, TileColor.GREEN, 4);
        assertFalse(checker.isCompleted(board, card));
    }

    @Test
    void squareGreenRotation() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, 1));
        addGreen(board, new Position(0, 2));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SQUARE, TileColor.GREEN, 4);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void squareGreenMissing() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, 1));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SQUARE, TileColor.GREEN, 4);

        assertFalse(checker.isCompleted(board, card));
    }

    @Test
    void straightGreen() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, -1));
        addGreen(board, new Position(2, -1));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.STRAIGHT, TileColor.GREEN, 3);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void StraightGreenRotation() {
        Board board = new Board();
        addGreen(board, new Position(1, -1));
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, 1));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.TRIANGLE, TileColor.GREEN, 3);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void snakeGreen() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, 1));
        addGreen(board, new Position(0, 2));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SNAKE, TileColor.GREEN, 3);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void snakeGreenRotation() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, -1));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SNAKE, TileColor.GREEN, 3);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void snakeGreenWrongColor() {
        Board board = new Board();
        addGreen(board, new Position(0, 1));
        addGreen(board, new Position(1, 0));
        addGreen(board, new Position(1, 1));
        board.addTile(new Tile(new Position(0, 2), TileColor.YELLOW));

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SNAKE, TileColor.GREEN, 3);

        assertFalse(checker.isCompleted(board, card));
    }


    @Test
    void squarePinkYellow() {
        Board board = new Board();

        board.addTile(new Tile(new Position(0, 1), TileColor.PINK));
        board.addTile(new Tile(new Position(1, 0), TileColor.YELLOW));
        board.addTile(new Tile(new Position(1, 1), TileColor.PINK));
        board.addTile(new Tile(new Position(0, 2), TileColor.YELLOW));

        Map<Position, TileColor> req = new HashMap<>();
        req.put(new Position(0, 0), TileColor.PINK);
        req.put(new Position(1, -1), TileColor.YELLOW);
        req.put(new Position(1, 0), TileColor.PINK);
        req.put(new Position(0, 1), TileColor.YELLOW);

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SQUARE,5,req);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void squarePinkYellowWrongColor() {
        Board board = new Board();

        board.addTile(new Tile(new Position(0, 1), TileColor.PINK));
        board.addTile(new Tile(new Position(1, 0), TileColor.YELLOW));
        board.addTile(new Tile(new Position(1, 1), TileColor.PINK));
        board.addTile(new Tile(new Position(0, 2), TileColor.PINK));

        Map<Position, TileColor> req = new HashMap<>();
        req.put(new Position(0, 0), TileColor.PINK);
        req.put(new Position(1, -1), TileColor.YELLOW);
        req.put(new Position(1, 0), TileColor.PINK);
        req.put(new Position(0, 1), TileColor.YELLOW);

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SQUARE, 5, req);

        assertFalse(checker.isCompleted(board, card));
    }

    @Test
    void squarePinkGreen() {
        Board board = new Board();

        board.addTile(new Tile(new Position(0, 1), TileColor.GREEN));
        board.addTile(new Tile(new Position(1, 0), TileColor.PINK));
        board.addTile(new Tile(new Position(1, 1), TileColor.GREEN));
        board.addTile(new Tile(new Position(0, 2), TileColor.PINK));

        Map<Position, TileColor> req = new HashMap<>();
        req.put(new Position(0, 0), TileColor.GREEN);
        req.put(new Position(1, -1), TileColor.PINK);
        req.put(new Position(1, 0), TileColor.GREEN);
        req.put(new Position(0, 1), TileColor.PINK);

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SQUARE, 4, req);

        assertTrue(checker.isCompleted(board, card));
    }

    @Test
    void squarePinkGreenMissing() {
        Board board = new Board();

        board.addTile(new Tile(new Position(0, 1), TileColor.GREEN));
        board.addTile(new Tile(new Position(1, 0), TileColor.PINK));
        board.addTile(new Tile(new Position(1, 1), TileColor.GREEN));

        Map<Position, TileColor> req = new HashMap<>();
        req.put(new Position(0, 0), TileColor.GREEN);
        req.put(new Position(1, -1), TileColor.PINK);
        req.put(new Position(1, 0), TileColor.GREEN);
        req.put(new Position(0, 1), TileColor.PINK);

        TileObjectiveCard card = new TileObjectiveCard(TilePatternType.SQUARE, 4, req);

        assertFalse(checker.isCompleted(board, card));
    }
}
