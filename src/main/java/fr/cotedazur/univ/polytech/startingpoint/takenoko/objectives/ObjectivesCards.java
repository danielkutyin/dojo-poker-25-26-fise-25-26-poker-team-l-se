package fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.Improvements;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.TileColor;

import java.util.ArrayList;
import java.util.List;

public class ObjectivesCards {

    public static List<GardnerObjectives> createGardenerObjectives() {

        List<GardnerObjectives> objectives = new ArrayList<>();

        // 🟡 JAUNE
        objectives.add(new GardnerObjectives(4, 1, TileColor.YELLOW, 5, Improvements.WATERSHED));
        objectives.add(new GardnerObjectives(4, 1, TileColor.YELLOW, 6, Improvements.NONE));
        objectives.add(new GardnerObjectives(3, 3, TileColor.YELLOW, 7, Improvements.NONE));

        // 🟢 VERT
        objectives.add(new GardnerObjectives(3, 4, TileColor.GREEN, 8, Improvements.NONE));

        objectives.add(new GardnerObjectives(4, 1, TileColor.GREEN, 4, Improvements.ENCLOSURE));
        objectives.add(new GardnerObjectives(4, 1, TileColor.GREEN, 4, Improvements.WATERSHED));
        objectives.add(new GardnerObjectives(4, 1, TileColor.GREEN, 5, Improvements.FERTILIZER));

        // 🟡 JAUNE (suite)
        objectives.add(new GardnerObjectives(4, 1, TileColor.YELLOW, 4, Improvements.ENCLOSURE));
        objectives.add(new GardnerObjectives(4, 1, TileColor.YELLOW, 5, Improvements.FERTILIZER));

        // 🌸 ROSE
        objectives.add(new GardnerObjectives(4, 1, TileColor.PINK, 5, Improvements.ENCLOSURE));
        objectives.add(new GardnerObjectives(4, 1, TileColor.PINK, 6, Improvements.WATERSHED));
        objectives.add(new GardnerObjectives(4, 1, TileColor.PINK, 6, Improvements.FERTILIZER));
        objectives.add(new GardnerObjectives(4, 1, TileColor.PINK, 7, Improvements.NONE));

        // 🟢 VERT (dernier)
        objectives.add(new GardnerObjectives(4, 1, TileColor.GREEN, 3, Improvements.NONE));

        // 🌸 ROSE (dernier)
        objectives.add(new GardnerObjectives(3, 2, TileColor.PINK, 6, Improvements.NONE));

        return objectives;
    }
}
