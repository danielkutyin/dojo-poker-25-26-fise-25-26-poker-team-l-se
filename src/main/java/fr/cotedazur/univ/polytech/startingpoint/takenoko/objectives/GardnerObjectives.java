package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Improvements;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

public  class GardnerObjectives {
    private TileColor color;
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
    public GardnerObjectives(int requiredBambous, int requiredTilesPlanted , TileColor color , int points , Improvements improvement) {
        if (color == null) throw new IllegalArgumentException("color cannot be null");
        if (requiredBambous < 0) throw new IllegalArgumentException("requiredBambous must be >= 0");
        if (requiredTilesPlanted <= 0) throw new IllegalArgumentException("requiredTilesPlanted must be > 0");
        this.color = color;
        this.requiredBambous = requiredBambous;
        this.requiredTilesPlanted = requiredTilesPlanted;
        this.points = points;
        this.improvement = improvement;
    }
    public int getPoints() {
        return points;
    }

    public int getRequiredTilesPlanted() {
        return requiredTilesPlanted;
    }

    public Improvements getImprovement() {
        return improvement;
    }
    @Override
    public String toString() {
        return "GardenerObj{color=" + color +
                ", bambous>=" + requiredBambous +
                ", tiles>=" + requiredTilesPlanted +
                ", improv=" + improvement +
                ", pts=" + points + "}";
    }


    /// *verifie chaque  tuiles de plateau si non return false*///
    public boolean isAchieved(Board board) {
        int validTiles = 0;

        for (Tile tile : board.getTiles()) {

            if (tile.getColor() != color) {
                continue;
            }

            if (tile.getNbBambous() < requiredBambous) {
                continue;
            }

            // Seulement si une amélioration est exigée
            if (improvement != Improvements.NONE &&
                    tile.getImprovement() != improvement) {
                continue;
            }

            validTiles++;

            if (validTiles >= requiredTilesPlanted) {
                return true; // early exit
            }
        }

        return false;
    }

    public boolean isIrrigated(Board board) {
        return false; /**pour plus tard**/
    }

    public boolean hasImprovement() {
        return false;
    }
}
