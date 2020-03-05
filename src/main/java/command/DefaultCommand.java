package command;

import dictionary.GameEntities;

public class DefaultCommand extends Command {

    public DefaultCommand() {}

    @Override
    public String apply(String object, GameEntities ge) {
        return "You cannot do that.";
    }
}