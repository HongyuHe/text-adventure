package command;
import dictionary.GameEntities;
import entity.*;

public abstract class Command { // NOSONAR - Command is intended to be used as an API, an interface is not appropriate here
    public abstract String apply(final String object, final GameEntities ge);
    public abstract String apply(final Area object, final GameEntities ge);
    public abstract String apply(final Item object, final GameEntities ge);
    public abstract String apply(final Npc object, final GameEntities ge);
    public abstract String apply(final Player object, final GameEntities ge);
    public abstract String apply(final Obstacle object, final GameEntities ge);
}
