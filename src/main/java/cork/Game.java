package cork;

import dictionary.GameEntities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Boolean playerWon = false;
    private List<String> previousCommands = new ArrayList<>();
    private GameEntities gameEntities;

    Game(String game) {
        gameEntities = Initializer.loadGameFiles(game);
    }

//    Boolean isGameOver() { return gameEntities.getPlayer().has(gameOverItem); // TODO: have has function implemented somewhere

    List<String> getPreviousCommands() { return previousCommands; }

    String handleCommand(String input) {
        String[] args = input.split(" ", 0);
        String  result = "";
        if(args.length == 1) {
            result = gameEntities.getPlayer().findCommandOrElse(args[0]).apply("", gameEntities);
        } else if(args.length == 2) {
            result = gameEntities.getPlayer().findCommandOrElse(args[0]).apply(args[1], gameEntities);
            previousCommands.add(input);
        } else if(args.length == 3) {
            result = gameEntities.getPlayer().findCommandOrElse(args[0]).apply(args[1], gameEntities);
            previousCommands.add(input);
        } else {
            System.out.println("You cannot do that!");
        }

        System.out.println(result);
        return result; // TODO: Make result make sense
    }
}
