package command;

import dictionary.GameEntities;
import entity.*;

public class ChangeState implements Command {
    private final Entity parent;

    public ChangeState(final Entity parent) { this.parent = parent; }

    @Override
    public String apply(final String object, final GameEntities ge) {
        if (!ge.getPlayer().hasInInventory(object)) { return String.format("You do not have '%s' in your inventory.", object); }
        if (!(parent instanceof Obstacle)) { return "That doesn't seem to do anything."; }

        Obstacle o = (Obstacle) parent;

        if (!o.getState()) { return "You don't need to do that."; }
        if (!o.getRequiredObject().equalsIgnoreCase(object)) { return "That doesn't work here."; }

        o.setState(false);

        return "It works!";
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