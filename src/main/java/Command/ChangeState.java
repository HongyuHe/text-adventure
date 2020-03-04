package Command;

import Entities.IInteractable;
import java.util.Set;

public class ChangeState implements ICommand {
    private IInteractable parent;
    private Set<String> args;

    public ChangeState(Set<String> args, IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ object + " change " + args;
    }
}