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

        if (!area.connectsTo(newLocation)) { return parent.getName() + " cannot go there from " + area.getName(); }

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

        StringBuilder result = new StringBuilder();

        if (parent.getType().equalsIgnoreCase("player"))
        {
            result.append(ge.getEntityOrDefault(newLocationName).getDescription());
        }
        else
        {
            result.append(parent.getName()).append(" moved to ").append(newLocation);
        }

        for (final Map.Entry<String, Integer> stat : area.getStats().entrySet())
        {
            final Integer oldValue = parent.getStatValue(stat.getKey());
            final Integer newValue = oldValue + stat.getValue();
            parent.setStat(stat.getKey(), newValue);

            result.append("\n").append(stat.getKey()).append(" changes to ").append(newValue);
        }

        removeEntityFromArea(parent, ge);
        parent.setCurrentLocation(newLocationName);
        addEntityToArea(parent, ge);

        return result.toString();
    }

    private void
    addEntityToArea(final Locatable targetEntity, final GameEntities ge)
    {
        final Area location = ge.getAreaOrDefault(targetEntity.getCurrentLocation());

        switch (targetEntity.getType()) {
            case "Obstacle":
                location.addObstacle(targetEntity.getName());
                break;
            case "NPC":
                location.addNpc(targetEntity.getName());
                break;
            default:
                break;
        }

    }

    private void
    removeEntityFromArea(final Locatable targetEntity, final GameEntities ge)
    {
        final Area location = ge.getAreaOrDefault(targetEntity.getCurrentLocation());

        switch (targetEntity.getType()) {
            case "Obstacle":
                location.removeObstacle(targetEntity.getName());
                break;
            case "NPC":
                location.removeNpc(targetEntity.getName());
                break;
            default:
                break;
        }

    }
}
