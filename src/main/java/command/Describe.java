package command;

import dictionary.GameEntities;
import entity.IInteractable;
import java.util.Set;

public class Describe extends Command {
    private IInteractable parent; // NOSONAR
    private Set<String> args; // NOSONAR

    public Describe(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object, final GameEntities ge) {
        if (object == null) { object = ge.getPlayer().getCurrentLocation(); }

        return ge.findEntityOrElse(object).getDescription();
    }
}