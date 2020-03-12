package command;
import dictionary.GameEntities;
import entity.*;

public interface Command {
    String apply(final String object,   final GameEntities ge);
    String apply(final Area object,     final GameEntities ge);
    String apply(final Item object,     final GameEntities ge);
    String apply(final Npc object,      final GameEntities ge);
    String apply(final Player object,   final GameEntities ge);
    String apply(final Obstacle object, final GameEntities ge);
}
