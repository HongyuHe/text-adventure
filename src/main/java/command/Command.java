package command;
import dictionary.GameEntities;

public abstract class Command {
    public abstract String apply(String object, GameEntities ge);
}
