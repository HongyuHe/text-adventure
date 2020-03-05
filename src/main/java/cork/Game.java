package cork;

import dictionary.GameEntities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<String> previousCommands = new ArrayList<>();
    private GameEntities gameEntities;

    Game(String game) { gameEntities = Initializer.loadGameFiles(game); }

    boolean isGameOver() { return (playerVictory() || gameEntities.getPlayer().isDead()); }

    boolean playerVictory() { return gameEntities.getPlayer().has(gameEntities.getGameOverItem().getName()); }

    List<String> getPreviousCommands() { return previousCommands; }

    String handleCommand(String input) {
        String  result = "";

        previousCommands.add(input);

        String[] args = input.split(" ", 0);

        if(args.length == 1) {
            result = gameEntities.getPlayer().findCommandOrElse(args[0]).apply(null, gameEntities);
        } else if(args.length == 2) {
            result = gameEntities.getPlayer().findCommandOrElse(args[0]).apply(args[1], gameEntities);
        } else if(args.length == 3) {
            result = gameEntities.getPlayer().findCommandOrElse(args[0]).apply(args[1], gameEntities);
        }

        return result;
    }
}
