package command;

import dictionary.GameEntities;

public class DefaultCommand extends Command {
    @Override
    public String apply(final String object, final GameEntities ge) { return "You cannot do that."; }
}