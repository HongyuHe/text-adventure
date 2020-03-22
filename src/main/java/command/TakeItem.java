package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Map;

public class TakeItem implements ICommand {
    private final Locatable parent;

    public TakeItem(final Locatable parent) {
        this.parent = parent;
    }

    @Override
    public String apply(String object, GameEntities ge) {
        Area area = ge.getAreaOrDefault(parent.getCurrentLocation());

        if (!area.hasInInventory(object)) { return String.format("You cannot see '%s' here.", object); }

        area.removeFromInventory(object);

        StringBuilder result = new StringBuilder(parent.getName() + " picked up " + object);

        parent.addToInventory(object);

        Item item = ge.getItemOrDefault(object);

        if (item.isConsumable()) { return result.toString(); }

        for (final Map.Entry<String, Integer> stat : item.getStats().entrySet())
        {
            final Integer oldValue = parent.getStatValue(stat.getKey());
            final Integer newValue = oldValue + stat.getValue();
            parent.setStat(stat.getKey(), newValue);

            result.append("\n").append(stat.getKey()).append(" changes to ").append(newValue);
        }

        return result.toString();
    }
}