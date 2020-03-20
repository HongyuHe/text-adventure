package command;

import dictionary.GameEntities;
import entity.*;

public class ListContents implements ICommand {
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
}