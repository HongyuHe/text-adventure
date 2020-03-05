package command;

import dictionary.GameEntities;
import entity.Area;
import entity.IInteractable;

import java.util.Set;

public class ChangeLocation extends Command {
    private IInteractable parent;
    private Set<String> args;

    public ChangeLocation(Set<String> args, IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String newLocation, GameEntities ge)
    {
        Area area = (Area) ge.findEntityOrElse(parent.getCurrentLocation());

        if (area.connectsTo(newLocation)) {
            parent.setCurrentLocation(area.getConnection(newLocation));
            return "You are now in the area: " + parent.getCurrentLocation();
        }
        return "Why not try flying to Mars instead?";
    }
}
