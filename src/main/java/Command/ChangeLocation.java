package Command;

import Entities.IInteractable;

import java.util.Set;

public class ChangeLocation implements ICommand {
    private IInteractable parent;
    private Set<String> args;

    public ChangeLocation(Set<String> args, IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ args + " change location to " + object;
    }
}
