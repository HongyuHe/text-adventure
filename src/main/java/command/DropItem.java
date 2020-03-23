package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Map;

public class DropItem implements ICommand {
    private final Entity parent;

    public DropItem(final Entity parent) { this.parent = parent; }

    @Override
    public String apply(String object, GameEntities ge) {
        if (!parent.hasInInventory(object)) { return String.format("You are not holding '%s'", object); }
        parent.removeFromInventory(object);

        Area area = ge.getAreaOrDefault(ge.getPlayer().getCurrentLocation());

        area.addToInventory(object);

        StringBuilder result = new StringBuilder(parent.getName() + " dropped " + object);

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
}