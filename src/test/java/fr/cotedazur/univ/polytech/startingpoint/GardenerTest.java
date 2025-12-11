package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.characters.Gardener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GardenerTest {

    @Test
    public void gardenerStartsOnPond() {
        Board board = new Board();
        Gardener gardener = board.getGardener();

        assertEquals(new Position(0, 0), gardener.getPos(),
                "Le jardinier doit commencer sur l'étang (0,0)");
    }

    @Test
    public void plantBambooOnGardenerTileIncreasesBambooCount() {
        Board board = new Board();
        Gardener gardener = board.getGardener();

        // On crée une tuile verte adjacente à l'étang, par exemple (0,1)
        Position pos = new Position(0, 1);
        Tile greenTile = new Tile(pos, TileColor.GREEN);
        board.addTile(greenTile);

        // On place le jardinier sur cette tuile
        gardener.setPos(pos);

        // Avant : 0 bambou
        assertEquals(0, greenTile.getNbBambous(), "Avant plantation, il ne doit pas y avoir de bambou");

        // Action : on appelle ta méthode
        board.plantBambooOnGardenerTile();

        // Après : 1 bambou
        assertEquals(1, greenTile.getNbBambous(),
                "Après plantBambooOnGardenerTile, il doit y avoir 1 bambou sur la tuile du jardinier");
    }
}