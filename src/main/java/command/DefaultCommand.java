package command;

import dictionary.GameEntities;
import entity.*;

public class DefaultCommand implements ICommand {
    private final Entity parent;

    public DefaultCommand(final Entity parent) { this.parent = parent; }

    @Override
    public String apply(final String object, final GameEntities ge)
    {
        if (ge.getEntityOrDefault(parent.getName()) == DefaultEntity.instance()) { return "You cannot see that here."; }
        else { return "You cannot do that."; }
    }
}