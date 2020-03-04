import Entities.GameEntities;
import Entities.Initializer;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String gameOverItem;
    private Boolean playerWon = false;
    private List<String> previousCommands = new ArrayList<>();

    GameEntities gameEntities = Initializer.loadGameFiles();

//    Boolean isGameOver() {
//        return gameEntities.getPlayer().has(gameOverItem); // TODO: have has function implemented somewhere
//    }

    List<String> getPreviousCommands() { return previousCommands; }

    String handleCommand(String input) {
        String[] args = input.split(" ", 0);
        if(args.length == 2) {
            gameEntities.getPlayer(). // TODO: eat apple >> findOrElse("player").takeActionOrDefault("eat).apply("apple)
//            System.out.println(args[0] + " " + args[1] + " player");
            previousCommands.add(input);
        } else if(args.length == 3) {
//            System.out.println(args[0] + " " + args[1] + " " + args[2]);
            previousCommands.add(input);
        } else {
            System.out.println("Invalid command\n");
        }
        return input;
    } // handle command and append to previousCommands return state to user as string
}
