package command;

import dictionary.GameEntities;
import entity.IInteractable;
import java.util.Set;

public class Describe extends Command {
    private IInteractable parent;
    private Set<String> args;

    public Describe(Set<String> args, IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object, GameEntities ge) {
        if (object == null) { object = ge.getPlayer().getCurrentLocation(); }

        return ge.findEntityOrElse(object).getDescription();
    }
}