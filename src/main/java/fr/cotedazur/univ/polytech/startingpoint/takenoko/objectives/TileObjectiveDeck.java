package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import java.util.*;

public class TileObjectiveDeck {
    private List<TileObjectiveCard> remaining;
    private Random random=new Random();

    public TileObjectiveDeck(List<TileObjectiveCard> initialCards){
        this.remaining=new ArrayList<>(initialCards);
        Collections.shuffle(this.remaining,random);
    }

    public boolean isEmpty(){
        return remaining.isEmpty();
    }

    public int remainingCount(){
        return remaining.size();
    }

    public Optional<TileObjectiveCard> draw(){
        if(remaining.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(remaining.remove(remaining.size()-1));
    }
}
