package cork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine {
    private Game currentGame;
    private UIHandler uiHandler = new UIHandler();

    public static void main (String[] args) {
        Engine engine = new Engine();
        engine.startMenu();
    }

//    String handleGameover() {}

    private List<String> loadGameList() {
        try (Stream<Path> walk = Files.walk(Paths.get(".\\games"), 1)) {
            List<String> gameList = walk.filter(Files::isDirectory).map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
            gameList.remove(0);
            return gameList;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.singletonList("No games found.");
    }

//    void startGame(game: Game) {}

    private void quit() { System.exit(0); }

    Boolean startNewGame() {
        uiHandler.clearScreen();
        uiHandler.print("Welcome");
        runGame();
        return true;
    }

    private Boolean loadSavedGame() {
        uiHandler.clearScreen();
        uiHandler.print("Loading is not yet supported.");
        if(uiHandler.promptUser("Return to main menu?")) {
            startMenu();
        } else {
            quit();
        }

        return false;
    }

//    cork.Game getSave() {}

    private Boolean saveGame() {
        uiHandler.print("Saving is not yet supported.");
        if(uiHandler.promptUser("Return to main menu?")) {
            startMenu();
        } else {
            quit();
        }

        return false;
    }

    public void startMenu() {
        uiHandler.clearScreen();
        String choice;

        choice = uiHandler.displayMainMenu();
        if(choice.equals("Quit")) { quit(); }

        choice = uiHandler.displayGamesMenu(loadGameList());
        if(choice.equals("Quit")) { quit(); } else { currentGame = new Game(choice); }

        choice = uiHandler.displayGameSubMenu(choice);
        if(choice.equals("New Game")) {
            startNewGame();
        } else if (choice.equals("Load Game")) {
            loadSavedGame();
        } else {
            quit();
        }
    }

    void runGame() {
        uiHandler.print(currentGame.handleCommand("look"));

        while(!currentGame.isGameOver()) {
            String userInput = uiHandler.getInput();
            if(userInput.equals("save")) {
                if(saveGame()) {
                    ; // TODO Implement
                }
            } else if(userInput.equals("load")) {
                if(loadSavedGame()) {
                    ; // TODO Implement
                }
            } else if(userInput.equals("quit")) {
                if(uiHandler.promptUser("Would you like to save before quitting?")) {
                    saveGame();
                }
                quit();
            } else {
                String result = currentGame.handleCommand(userInput);
                uiHandler.print(result);
            }
        }
    }
}
