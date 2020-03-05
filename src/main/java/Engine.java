import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine {
    List<Game> games;
    Game currentGame;

    private UIHandler UI = new UIHandler();
    private Game game = new Game();

//    String handleGameover() {}

    private List<String> loadGameList() {
        try (Stream<Path> walk = Files.walk(Paths.get(".\\savefiles"))) {
            List<String> gameList = walk.filter(Files::isDirectory).map(Path::toString).collect(Collectors.toList());
            gameList.remove(0);
            return gameList;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    void startGame(game: Game) {}

    private void quit() {
        System.out.println("Quitting...\n");
    }

//    Boolean startNewGame() {
//        currentGame = new Game();
//    }

    private Boolean loadSavedGame() { // TODO: implement
        System.out.println("Loading...\n");
        return true;
    }

//    Game getSave() {}

    private Boolean saveGame() { // TODO: implement
        System.out.println("Saving...\n");
        return true;
    }

    void startMenu() {
        if(UI.displayMainMenu()) {
            String choice = UI.displayGamesMenu(loadGameList());
            if(choice.compareTo("quit") == 0) {
                quit();
            } else {
                UI.displayGameSubMenu(choice);
            }
        } else {
            quit();
        }
    }

    void runGame() {
        while(true) {
            String userInput = UI.getInput();
            if(userInput.compareTo("save") == 0) {
                if(saveGame()) { System.out.println("Successfully saved game!"); }
            } else if(userInput.compareTo("load") == 0) {
                if(loadSavedGame()) { System.out.println("Successfully loaded game!"); }
            } else if(userInput.compareTo("quit") == 0) {
                quit();
            } else {
                game.handleCommand(userInput);
            }
            System.out.println("Prev commands: " + game.getPreviousCommands().toString());
        }
    }
}
