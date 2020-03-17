package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Map;

public class DropItem implements Command {
    private final Locatable parent;

    public DropItem(final Locatable parent) { this.parent = parent; }

    @Override
    public String apply(String object, GameEntities ge) {
        if (!parent.hasInInventory(object)) { return String.format("You are not holding '%s'", object); }
        parent.removeFromInventory(object);

        Area area = ge.getAreaOrDefault(parent.getCurrentLocation());

        area.addToInventory(object);

        StringBuilder result = new StringBuilder(object + " -> " + area.getName());

        Item item = ge.getItemOrDefault(object);
        if(item.isConsumable()) { return result.toString(); }

        for (final Map.Entry<String, Integer> stat : item.getStats().entrySet())
        {
            final Integer oldValue = parent.getStatValue(stat.getKey());
            final Integer newValue = oldValue - stat.getValue();
            parent.setStat(stat.getKey(), newValue);

            result.append("\n").append(stat.getKey()).append(" changes to ").append(newValue);
        }

        return result.toString();
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