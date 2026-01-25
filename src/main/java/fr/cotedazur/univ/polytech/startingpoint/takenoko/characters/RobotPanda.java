package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.Actions;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.SmartPandaStrategy;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Position;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TurnView;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesDeck;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesPanda;

public class RobotPanda extends Robot{
    private final Board boardRef;
    private final SmartPandaStrategy strategy = new SmartPandaStrategy();
    public RobotPanda(Board boardRef) {
        super(boardRef);
        this.boardRef = boardRef;
    }
    @Override
    public void playTurn(ObjectivesPanda pandaDeck, ObjectivesDeck gardenerDeck) {
        TurnView view = new TurnView(boardRef, this);

        Actions a1 = strategy.choose(view);
        applyAction(a1, pandaDeck, gardenerDeck);

        // refresh view (au cas où le board a changé)
        view = new TurnView(boardRef, this);

        Actions a2 = strategy.choose(view);
        applyAction(a2, pandaDeck, gardenerDeck);
    }

    @Override
    public Position playPandaMove() {
        TurnView view = new TurnView(boardRef, this);

        // la stratégie calcule la destination
        return strategy.choosePandaDestination(view).map(pos -> {
                    boardRef.getPanda().setPos(pos);
                    return pos;
                })
                // sinon comportement random normal
                .orElseGet(super::playPandaMove);
    }
}

