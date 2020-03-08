package command;

import dictionary.GameEntities;
import entity.IInteractable;
import java.util.Set;

public class ChangeState extends Command {
    private IInteractable parent; // NOSONAR
    private Set<String> args;

    public ChangeState(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(final String object, final GameEntities ge) {
        return "Action>>>$ "+ object + " change " + args;
    }
}