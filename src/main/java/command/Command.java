package command;
import dictionary.GameEntities;
import entity.*;

public interface Command { // NOSONAR - Command is intended to be used as an API, an interface is not appropriate here
    String apply(final String object, final GameEntities ge);
    String apply(final Area object, final GameEntities ge);
    String apply(final Item object, final GameEntities ge);
    String apply(final Npc object, final GameEntities ge);
    String apply(final Player object, final GameEntities ge);
    String apply(final Obstacle object, final GameEntities ge);
}


// TODO: make Command an interface and have each type of command take a specific argument to allow a specific type of parent