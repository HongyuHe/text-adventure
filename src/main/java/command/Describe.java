package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Set;

public class Describe extends Command {
    private IInteractable parent;
    private Set<String> args;

    public Describe(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object, final GameEntities ge) {
        if (object.equals("")) { object = ge.getPlayer().getCurrentLocation(); }

        Entity e = ge.findEntityOrElse(object);

        if (!e.isActive()) { return String.format("You cannot see '%s'.", object); }

        return e.getDescription();
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