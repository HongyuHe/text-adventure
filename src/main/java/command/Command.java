package command;
import dictionary.GameEntities;

public abstract class Command { // NOSONAR - Command is intended to be used as an API, an interface is not appropriate here
    public abstract String apply(String object, GameEntities ge);
}
