package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Set;

public class ChangeStat extends Command {
    private IInteractable parent; // NOSONAR
    private Set<String> args;

    public ChangeStat(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(final String object, final GameEntities ge) {
        if (!(parent instanceof ICharacter)) { return "You cannot do that."; }

        Stat s = ge.getItemOrElse(object).getStat();

        ICharacter p = (ICharacter) parent;
        final Integer oldValue = p.getStatValue(s.getName());
        p.setStat(s.getName(), oldValue + s.getValue());

        p.removeFromInventory(object); // TODO: set active to false and do garbage collection?

        return String.format("%s increases to %d", s.getName(), oldValue + s.getValue());
    }
}