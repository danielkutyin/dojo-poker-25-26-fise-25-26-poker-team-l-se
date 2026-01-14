package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Improvements;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

public class GardnerObjectives {
    TileColor color;
    private int requiredBambous;
    private int requiredTilesPlanted;
    private final int points;
    private final Improvements improvement;


    public TileColor getColor() {
        return color;
    }
    public void setColor(TileColor color) {
        this.color = color;
    }
    public int getRequiredBambous() {
        return requiredBambous;
    }
    public void setRequiredBambous(int requiredBambous) {
        this.requiredBambous = requiredBambous;
    }
    public GardnerObjectives(int requiredBambous, int requiredTilesPlanted , TileColor color ,  int points ,  Improvements improvement) {
        this.color = color;
        this.requiredBambous = requiredBambous;
        this.requiredTilesPlanted = requiredTilesPlanted;
        this.points = points;
        this.improvement = improvement;
    }
    /// *verifie chaque  tuiles de plateau si non return false*///
    public boolean isAchieved(Board board) {
        int checkedTiles = 0;

        for (Tile tile : board.getTiles()) {

            // couleur
            if (tile.getColor() != color) {
                continue;
            }

            // hauteur minimale
            if (tile.getNbBambous() < requiredBambous) {
                continue;
            }

            // amélioration
            if (improvement != Improvements.NONE) {
                if (tile.getImprovement() != improvement) {
                    continue;
                }
            } else {
                if (tile.getImprovement() != Improvements.NONE) {
                    continue;
                }
            }

            checkedTiles++;
        }

        return checkedTiles >= requiredTilesPlanted;
    }



}
