package command;

import dictionary.GameEntities;
import entity.Area;
import entity.IInteractable;

import java.util.Set;

public class ChangeLocation extends Command {
    private IInteractable parent;
    private Set<String> args; // NOSONAR

    public ChangeLocation(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(final String newLocation, final GameEntities ge) {
        Area area = (Area) ge.findEntityOrElse(parent.getCurrentLocation());

        if (!area.connectsTo(newLocation)) { return "Why not try flying to Mars instead?"; }

        final String newLocationName = area.getConnection(newLocation);
        parent.setCurrentLocation(newLocationName);

        return ge.findEntityOrElse(newLocationName).getDescription();
    }
}
