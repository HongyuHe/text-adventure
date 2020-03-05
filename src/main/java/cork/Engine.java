package cork;

import org.tinylog.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine {
    enum State {HOME_SCREEN, MAIN_MENU, GAME_MENU, GAME_RUNNING, QUIT }

    private State state = State.HOME_SCREEN;
    private Game currentGame;
    private String gameName = "";
    private UIHandler uiHandler = new UIHandler();

    public static void main (String[] args) {
        Engine engine = new Engine();
        engine.run();
    }

    public void run()
    {
        while (state != State.QUIT)
        {
            switch (state)
            {
                case HOME_SCREEN:
                    displayHomeScreen();
                    break;
                case MAIN_MENU:
                    displayMainMenu();
                    break;
                case GAME_MENU:
                    displayGameMenu();
                    break;
                case GAME_RUNNING:
                    runGame();
                    break;
                default:
                    break;
            }
        }

        uiHandler.exit();
    }

    private void changeState(final String choice)
    {
        if (choice.equalsIgnoreCase("quit")) {
            state = State.QUIT;
        } else {
            switch (state)
            {
                case HOME_SCREEN:
                    state = State.MAIN_MENU;
                    break;
                case MAIN_MENU:
                    gameName = choice;
                    state = State.GAME_MENU;
                    break;
                case GAME_MENU:
                    if (choice.equals("Load Game")) { loadGame(); }
                    state = State.GAME_RUNNING;
                    break;
                case GAME_RUNNING:
                    state = State.HOME_SCREEN;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayHomeScreen()
    {
        changeState(uiHandler.displayHomeScreen());
    }

    private void displayMainMenu()
    {
        changeState(uiHandler.displayMainMenu(loadGameList()));
    }

    private void displayGameMenu()
    {
        currentGame = new Game(gameName);
        changeState(uiHandler.displayGameMenu(gameName));
    }

    private List<String> loadGameList() {
        final String GAMES_DIRECTORY_PATH = "./games";

        try (Stream<Path> paths = Files.find(Paths.get(GAMES_DIRECTORY_PATH), 1, (path, attributes) -> attributes.isDirectory())) {
            List<String> gameList = paths.map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
            gameList.remove(0);
            return gameList;
        }
        catch (IOException e) {
            Logger.error(e.getMessage());
        }

        return Collections.singletonList("No games found.");
    }

    private void loadGame() {
        uiHandler.print("Loading is not yet supported. Hit enter to continue.");
        uiHandler.getInput();
    }

    private void saveGame() {
        uiHandler.print("Saving is not yet supported. Hit enter to continue.");
        uiHandler.getInput();
    }

    private void runGame() {
        uiHandler.clearScreen();
        uiHandler.print("Please be aware that this is not yet a complete deliverable - only moving is supported currently.\n" +
                "You can use the command \"move\" coupled with a direction or location to get around, e.g. \"move castle\" or \"move north\".\n" +
                "The command \"look\" can be used on its own, or coupled with the name of an item, e.g. \"look apple\".\n" +
                "Type \"quit\" to exit the game.\n\n");
        uiHandler.print(currentGame.handleCommand("look"));

        while(!currentGame.isGameOver()) {
            final String userInput = uiHandler.getInput();

            switch (userInput) {
                case "save":
                    saveGame();
                    break;
                case "load":
                    loadGame();
                    break;
                case "quit":
                    if (uiHandler.promptUser("Would you like to save before quitting?")) { saveGame(); }
                    state = State.QUIT;
                    return;
                default:
                    String result = currentGame.handleCommand(userInput);
                    uiHandler.print(result);
                    break;
            }
        }
    }
}
