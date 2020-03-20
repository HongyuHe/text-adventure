package command;

import dictionary.GameEntities;
import entity.*;

public class DefaultCommand implements ICommand {
    @Override
    public String apply(final String object, final GameEntities ge) { return "You cannot do that."; }
}