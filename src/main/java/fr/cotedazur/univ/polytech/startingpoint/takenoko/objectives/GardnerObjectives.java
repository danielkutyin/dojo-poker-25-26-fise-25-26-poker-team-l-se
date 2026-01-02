package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Board;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Tile;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

public  class GardnerObjectives {
    private TileColor color;
    private int requiredBambous;
    private int requiredTilesPlanted;


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
    public GardnerObjectives(int requiredBambous, int requiredTilesPlanted , TileColor color) {
        if (color == null) throw new IllegalArgumentException("color cannot be null");
        if (requiredBambous < 0) throw new IllegalArgumentException("requiredBambous must be >= 0");
        if (requiredTilesPlanted <= 0) throw new IllegalArgumentException("requiredTilesPlanted must be > 0");
        this.color = color;
        this.requiredBambous = requiredBambous;
        this.requiredTilesPlanted = requiredTilesPlanted;
    }

    public boolean isAchieved(Board board) {
        int checkedTiles = 0;
        for (Tile tile : board.getTiles()) {

            if (tile.getColor() == color && tile.getNbBambous() >= requiredBambous) {
                checkedTiles++;
            }
        }
        return checkedTiles >= requiredTilesPlanted;
    }
    public boolean isIrrigated(Board board) {
        return false; /**pour plus tard**/
    }

    public boolean hasImprovement() {
        return false;
    }
}
