package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Map;

public class ListStats implements ICommand {
    private final Entity parent;

    public ListStats(final Entity parent) { this.parent = parent; }

    @Override
    public String apply(final String object, final GameEntities ge)
    {
        StringBuilder contents = new StringBuilder("Stats:\n");
        Entity target = ge.getEntityOrDefault(object);
        for (final Map.Entry<String, Integer> stat : target.getStats().entrySet()) {
            contents.append(String.format("\t%s: %d%n", stat.getKey(), stat.getValue()));
        }

        return contents.toString();
    }
}