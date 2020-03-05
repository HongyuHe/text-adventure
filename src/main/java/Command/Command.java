package Command;
import Dictionary.GameEntities;

public abstract class Command {
    public abstract String apply(String object, GameEntities ge);
}
