package fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TurnView;

public class StrategyController implements PlayerController {
    private final RobotStrategy strategy;

    public StrategyController(RobotStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Actions decide(TurnView view) {
        return strategy.choose(view.robot(), view.board());
    }
}
