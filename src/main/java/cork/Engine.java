package cork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine {
    enum State {HOME_SCREEN, MAIN_MENU, GAME_MENU, GAME_RUNNING, QUIT }

    private static final String GAMES_DIRECTORY_PATH = "./games";
    private static final String SAVE_FILE_NAME = "save.txt";

    private static final String QUIT_COMMAND = "quit";
    private static final String SAVE_COMMAND = "save";
    private static final String LOAD_COMMAND = "load";

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
        if (choice.equalsIgnoreCase(QUIT_COMMAND)) {
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
                    if (choice.equals(UIHandler.LOAD_GAME_OPTION)) { loadGame(); }
                    state = State.GAME_RUNNING;
                    break;
                case GAME_RUNNING:
                    state = State.HOME_SCREEN;
                    break;
                default:
                    state = State.QUIT;
                    break;
            }
        }
    }

    private void displayHomeScreen()
    {
        changeState(uiHandler.displaySplashScreen());
    }

    private void displayMainMenu()
    {
        try { changeState(uiHandler.displayMainMenu(loadGameList())); }
        catch (IOException e) {
            state = State.QUIT;
            uiHandler.displayError("Please ensure that the Cork JAR is in the same directory as the 'games' folder and that the folder is populated.");
        }
    }

    private void displayGameMenu()
    {
        currentGame = new Game(gameName);
        changeState(uiHandler.displayGameMenu(gameName));
    }

    private List<String> loadGameList() throws IOException {
        try (Stream<Path> paths = Files.find(Paths.get(GAMES_DIRECTORY_PATH), 1, (path, attributes) -> attributes.isDirectory())) {
            List<String> gameList = paths.map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
            gameList.remove(0);

            if (gameList.isEmpty()) { throw new IOException("Games folder empty."); }

            return gameList;
        }
    }

    private Path getSaveFilePath()
    {
        return Paths.get(String.format("%s/%s/%s", GAMES_DIRECTORY_PATH, gameName, SAVE_FILE_NAME));
    }

    private void loadGame() {
        try {
            for (final String input : Files.readAllLines(getSaveFilePath())) { currentGame.handleCommand(input); }
            uiHandler.print("Game loaded.");
        } catch (IOException e) {
            uiHandler.displayError("Game could not be loaded.");
        }
    }

    private void saveGame() {
        try {
            Files.write(getSaveFilePath(), currentGame.getPreviousCommands());
            uiHandler.print("Game saved. Press any key to continue.");
            uiHandler.getInput();
        }
        catch (IOException e) {
            uiHandler.displayError("Game could not be saved.");
        }
    }

    private void runGame() {
        uiHandler.clearScreen();
        uiHandler.print("Please be aware that this is not yet a complete deliverable - only moving is supported currently.\n" +
                "You can use the command \"move\" (or \"go\") coupled with a direction or location to get around, e.g. \"move castle\" or \"move north\".\n" +
                "The command \"look\" can be used on its own, or coupled with the name of an item, e.g. \"look apple\".\n" +
                "Type \"quit\" to exit the game.\n\n");
        uiHandler.print(currentGame.handleCommand("look"));

        while(!currentGame.isGameOver()) {
            final String userInput = uiHandler.getInput();

            switch (userInput) {
                case SAVE_COMMAND:
                    saveGame();
                    break;
                case LOAD_COMMAND:
                    loadGame();
                    break;
                case QUIT_COMMAND:
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
