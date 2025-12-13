package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PandaTest {
    @Test
    public void pandaStartsOnPondTest() {
        Panda po=new Panda();
        assertEquals(new Position(0,0),po.getPos());
    }

    @Test
    public void pandaPositionCanBeChangedTest() {
        Panda po=new Panda();
        Position pos=new Position(2,-1);
        po.setPos(pos);
        assertEquals(pos,po.getPos());
    }
}
