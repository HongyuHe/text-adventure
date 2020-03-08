package command;

import dictionary.GameEntities;
import entity.*;

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

        Stat s = i.getStat();

        final Integer oldValue = p.getStatValue(s.getName());
        p.setStat(s.getName(), oldValue + s.getValue());

        p.removeFromInventory(object);
        i.setActive(false);

        return String.format("%s changes to %d", s.getName(), oldValue + s.getValue());
    }
}