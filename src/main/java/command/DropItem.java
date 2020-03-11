package command;

import dictionary.GameEntities;
import entity.*;

public class DropItem implements Command {
    private final Locatable parent;

    public DropItem(final Locatable parent) { this.parent = parent; }

    @Override
    public String apply(String object, GameEntities ge) {
        if (!parent.hasInInventory(object)) { return String.format("You are not holding '%s'", object); }
        parent.removeFromInventory(object);

        Area area = ge.getAreaOrElse(parent.getCurrentLocation());

        area.addToInventory(object);

        return object + " -> " + area.getName();
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