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
    private UIHandler UI = new UIHandler();

//    String handleGameover() {}

    private List<String> loadGameList() {
        try (Stream<Path> walk = Files.walk(Paths.get(".\\games"))) {
            List<String> gameList = walk.filter(Files::isDirectory).map(Path::toString).collect(Collectors.toList());
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

    Boolean startNewGame() { // TODO: Implement
        UI.clearScreen();
        UI.print("Welcome");
        runGame();
        return true;
    }

    private Boolean loadSavedGame() { // TODO: implement
        UI.clearScreen();
        UI.print("Loading is not yet supported.");
        if(UI.promptUser("Return to main menu?")) {
            startMenu();
        } else {
            quit();
        }
//        runGame();
        return false;
    }

//    Game getSave() {}

    private Boolean saveGame() { // TODO: implement
        UI.print("Saving is not yet supported.");
        if(UI.promptUser("Return to main menu?")) {
            startMenu();
        } else {
            quit();
        }
        return false;
    }

    void startMenu() {
        UI.clearScreen();
        String choice;

        choice = UI.displayMainMenu();
        if(choice.compareTo("Quit") == 0) {
            quit();
        }

        choice = UI.displayGamesMenu(loadGameList());
        if(choice.compareTo("Quit") == 0) {
            quit();
        } else {
            currentGame = new Game(choice); //TODO: set current game
        }

        choice = UI.displayGameSubMenu(choice);
        if(choice.compareTo("New Game") == 0) {
            startNewGame();
        } else if (choice.compareTo("Load Game") == 0) {
            loadSavedGame();
        } else {
            quit();
        }
    }

    void runGame() {
        while(true) {
            String userInput = UI.getInput();
            if(userInput.compareTo("save") == 0) {
                if(saveGame()) {
                    ; // TODO Implement
                }
            } else if(userInput.compareTo("load") == 0) {
                if(loadSavedGame()) {
                    ; // TODO Implement
                }
            } else if(userInput.compareTo("quit") == 0) {
                if(UI.promptUser("Would you like to save before quitting?")) {
                    saveGame();
                }
                quit();
            } else {
                String result = currentGame.handleCommand(userInput);
                UI.print(result);
            }
        }
    }
}
