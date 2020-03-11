package command;

import dictionary.GameEntities;
import entity.*;

public class ListContents implements Command {
    private final Entity parent;

    public ListContents(final Entity parent) { this.parent = parent; }

    @Override
    public String apply(final String object, final GameEntities ge)
    {
        final String DELIMITER = ", ";

        StringBuilder contents = new StringBuilder("Contents: ");

        for (final String item : parent.getInventory()) { contents.append(item).append(DELIMITER); }
        if (!parent.getInventory().isEmpty()) { contents.delete(contents.length() - DELIMITER.length(), contents.length()); }

        return contents.toString();
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