package command;

import dictionary.GameEntities;
import entity.*;

public class Describe implements Command {
    private final Entity parent;

    public Describe(final Entity parent) { this.parent = parent; }

    @Override
    public String apply(String object, final GameEntities ge) {
        if (!parent.isActive()) { return String.format("You cannot see '%s'.", object); }

        return parent.getDescription();
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