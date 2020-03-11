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
        String  result = "";

        previousCommands.add(input);

        String[] args = input.split(" ", 0);

        if(args.length == 1) {
            result = gameEntities.getPlayer().findCommandOrElse(args[0]).apply("", gameEntities);
        } else if(args.length == 2) {
            result = gameEntities.getPlayer().findCommandOrElse(args[0]).apply(args[1], gameEntities);
        } else if(args.length == 3) {
            result = gameEntities.findEntityOrElse(args[2]).findCommandOrElse(args[0]).apply(args[1], gameEntities);
        }

        return result;
    }
}
