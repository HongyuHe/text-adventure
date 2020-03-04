package Command;
import Dictionary.GameEntities;

public interface ICommand {
    String apply(String object, GameEntities ge);
}
