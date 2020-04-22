package command;

import dictionary.GameEntities;
import entity.*;

public class ChangeState implements ICommand {
    private final Obstacle parent;

    public ChangeState(final Obstacle parent) { this.parent = parent; }

    @Override
    public String apply(final String object, final GameEntities ge) {
        if (!ge.getPlayer().hasInInventory(object)) { return String.format("You do not have '%s' in your inventory.", object); }
        if (!parent.getState()) { return "You don't need to do that."; }
        if (!parent.getRequiredObject().equalsIgnoreCase(object)) { return "That doesn't work here."; }

        parent.deactivate();

        return "The path to " + parent.getBlocks() + " is now clear!";
    }
}