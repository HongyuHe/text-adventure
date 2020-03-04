package Command;

import Dictionary.GameEntities;
import Entities.IInteractable;
import java.util.Set;

public class Describe implements ICommand {
    private IInteractable parent;
    private Set<String> args;

    public Describe(Set<String> args, IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object, GameEntities ge) {
        return "Action>>>$ "+ "Describe " + args;
    }
}