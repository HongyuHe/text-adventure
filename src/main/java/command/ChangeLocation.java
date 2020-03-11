package command;

import dictionary.GameEntities;
import entity.*;

public class ChangeLocation implements Command {
    private final Locatable parent;

    public ChangeLocation(final Locatable parent) { this.parent = parent; }

    @Override
    public String apply(final String newLocation, final GameEntities ge) {
        Area area = ge.getAreaOrElse(parent.getCurrentLocation());

        if (!area.connectsTo(newLocation)) { return "You cannot go there from here."; }

        for (final String o : area.getObstacles())
        {
            final Obstacle obstacle = ge.getObstacleOrElse(o);
            final String nextArea   = area.getConnection(newLocation);
            if (obstacle.getState() && obstacle.getBlocks().equalsIgnoreCase(nextArea))
            {
                return String.format("There is a %s in the way.", obstacle.getName());
            }
        }

        final String newLocationName = area.getConnection(newLocation);
        parent.setCurrentLocation(newLocationName);

        return ge.findEntityOrElse(newLocationName).getDescription();
    }

    public String apply(final Area object, final GameEntities ge) {
        return "";
    }

    public String apply(final Item object, final GameEntities ge) {
        return "";
    }

    public String apply(final Npc object, final GameEntities ge) {
        return "";
    }

    public String apply(final Obstacle object, final GameEntities ge) {
        return "";
    }

    public String apply(final Player object, final GameEntities ge) {
        return "";
    }
}
