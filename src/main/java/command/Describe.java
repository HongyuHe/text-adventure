package command;

import dictionary.GameEntities;
import entity.*;

public class Describe implements ICommand {
    private final Entity parent;

    public Describe(final Entity parent) { this.parent = parent; }

    @Override
    public String apply(String object, final GameEntities ge) {
        if (object.equals("")) { object = ge.getPlayer().getCurrentLocation(); }

        Entity e = ge.getEntityOrDefault(object);

        if (!e.isActive()) { return String.format("You cannot see '%s'.", object); }

        return e.getDescription();
    }
}