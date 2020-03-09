package command;

import dictionary.GameEntities;
import entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChangeStat extends Command {
    private IInteractable parent;
    private Set<String> args;

    public ChangeStat(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(final String object, final GameEntities ge) {
        if (!(parent instanceof ICharacter)) { return "You cannot do that."; }

        ICharacter p = (ICharacter) parent;

        if (!p.hasInInventory(object)) { return String.format("You do not have '%s' in your inventory.", object); }

        Item i = ge.getItemOrElse(object);
        if (!i.isConsumable()) { return "You cannot use that."; }

        StringBuilder result = new StringBuilder();
        for (final Map.Entry<String, Integer> stat : i.getStats().entrySet())
        {
            final Integer oldValue = p.getStatValue(stat.getKey());
            final Integer newValue = oldValue + stat.getValue();
            p.setStat(stat.getKey(), newValue);

            result.append("\n").append(stat.getKey()).append(" changes to ").append(newValue);
        }

        p.removeFromInventory(object);
        i.setActive(false);

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