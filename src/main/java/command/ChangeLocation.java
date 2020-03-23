package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Map;

public class ChangeLocation implements ICommand {
    private final Locatable parent;

    public ChangeLocation(final Locatable parent) { this.parent = parent; }

    @Override
    public String apply(final String newLocation, final GameEntities ge) {
        Area area = ge.getAreaOrDefault(parent.getCurrentLocation());

        if (!area.connectsTo(newLocation)) { return "You cannot go there from here."; }

        for (final String o : area.getObstacles())
        {
            final Obstacle obstacle = ge.getObstacleOrDefault(o);
            final String nextArea   = area.getConnection(newLocation);
            if (obstacle.getState() && obstacle.getBlocks().equalsIgnoreCase(nextArea))
            {
                return obstacle.getMessage();
            }
        }

        final String newLocationName = area.getConnection(newLocation);
        area = ge.getAreaOrDefault(newLocationName);

        StringBuilder result = new StringBuilder(ge.getEntityOrDefault(newLocationName).getDescription());

        for (final Map.Entry<String, Integer> stat : area.getStats().entrySet())
        {
            final Integer oldValue = parent.getStatValue(stat.getKey());
            final Integer newValue = oldValue + stat.getValue();
            parent.setStat(stat.getKey(), newValue);

            result.append("\n").append(stat.getKey()).append(" changes to ").append(newValue);
        }

        parent.setCurrentLocation(newLocationName);

        return result.toString();
    }
}
