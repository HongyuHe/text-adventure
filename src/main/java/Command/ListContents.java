package Command;

import Entities.IInteractable;

import java.util.Set;

public class ListContents implements ICommand {
    private IInteractable parent;
    private Set<String> args;

    public ListContents(Set<String> args, IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ "List contents of " + args;
    }
}