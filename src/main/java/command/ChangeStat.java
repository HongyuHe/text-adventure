package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Map;

public class ChangeStat implements ICommand {
    private final Entity parent;

    public ChangeStat(final Entity parent) { this.parent = parent; }

    @Override
    public String apply(final String object, final GameEntities ge) {
        if (!ge.getPlayer().hasInInventory(parent.getName())
                && !ge.getPlayer().hasInInventory(object))
        {
            return String.format("You do not have '%s' in your inventory.", object);
        }

        Item item = ge.getItemOrDefault(object);
        if (!item.isConsumable()) { return "You cannot use that."; }

        StringBuilder result = new StringBuilder();
        result.append(parent.getName()).append(":");
        for (final Map.Entry<String, Integer> stat : item.getStats().entrySet())
        {
            final Integer oldValue = parent.getStatValue(stat.getKey());
            final Integer newValue = oldValue + stat.getValue();
            parent.setStat(stat.getKey(), newValue);

            result.append("\n").append(stat.getKey()).append(" changes to ").append(newValue);
        }

        ge.getPlayer().removeFromInventory(object);
        item.setActive(false);

        return result.toString();
    }
}