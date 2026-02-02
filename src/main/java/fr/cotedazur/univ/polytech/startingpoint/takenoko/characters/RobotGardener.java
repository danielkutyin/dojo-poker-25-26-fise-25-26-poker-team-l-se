package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.Actions;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.BasicGardnerStrategy;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TurnView;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesDeck;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesPanda;

public class RobotGardener extends Robot {
    private Board boardRef;
    private BasicGardnerStrategy strat = new BasicGardnerStrategy();

    public RobotGardener(Board boardRef) {
        super(boardRef);
        this.boardRef = boardRef;
    }
    @Override
    public void playTurn(ObjectivesPanda pandaDeck, ObjectivesDeck gardenerDeck) throws Exception {
        TurnView view = new TurnView(boardRef, this);

        Actions a1 = strat.choose(view);
        applyAction(a1, pandaDeck, gardenerDeck);

        // refresh view (au cas où le board a changé)
        view = new TurnView(boardRef, this);

        Actions a2 = strat.choose(view);
        applyAction(a2, pandaDeck, gardenerDeck);
    }

}
