package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.Actions;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.SmartPandaStrategy;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TurnView;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesDeck;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesPanda;

public class RobotGardener extends Robot{
    private Board boardRef;

    public RobotGardener(Board boardRef){
        super( boardRef);
        this.boardRef = boardRef;
    }
}
