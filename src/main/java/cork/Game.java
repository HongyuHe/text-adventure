package cork;

import dictionary.GameEntities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<String> previousCommands = new ArrayList<>();
    private final GameEntities gameEntities;

    public
    Game(final String game) { gameEntities = Initializer.loadGameFiles(game); }

    public boolean
    isGameOver() { return (playerVictory() || gameEntities.getPlayer().isDead()); }

    public boolean
    playerVictory() { return gameEntities.getPlayer().hasInInventory(gameEntities.getGameOverItem().getName()); }

    public List<String>
    getPreviousCommands() { return new ArrayList<>(previousCommands); }

    public String
    handleCommand(final String input)
    {
        final String SPLIT_DELIMITER = " ";
        final String[] args = input.split(SPLIT_DELIMITER);

        previousCommands.add(input);

        switch (args.length)
        {
            case 0:  return "Commands must consist of at least one word.";
            case 1:  return gameEntities.getPlayer().findCommandOrDefault(args[0]).apply("", gameEntities);
            case 2:  return gameEntities.getPlayer().findCommandOrDefault(args[0]).apply(args[1], gameEntities);
            case 3:  return gameEntities.getEntityOrDefault(args[2]).findCommandOrDefault(args[0]).apply(args[1], gameEntities);
            default: return "Commands should only consist of three words at maximum.";
        }
    }
}
