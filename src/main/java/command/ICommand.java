package command;

import dictionary.GameEntities;

public interface ICommand {
    String apply(final String object, final GameEntities ge);
}
