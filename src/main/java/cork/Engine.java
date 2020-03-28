package cork;

import cork.engine_state.EngineStateMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine {
    private static final String GAMES_DIRECTORY_PATH = "./games";
    private static final String SAVE_FILE_NAME = "save.txt";

    private static final String QUIT_COMMAND = "quit";
    private static final String SAVE_COMMAND = "save";
    private static final String LOAD_COMMAND = "load";

    private EngineStateMachine state = EngineStateMachine.instance();

    private boolean running = true;
    private Game currentGame;
    private String gameName = "";
    private final UIHandler uiHandler = new UIHandler();


    public static void
    main (String[] args)
    {
        Engine engine = new Engine();
        engine.run();
    }

    public void
    run()
    {
        while (running) { state.execute(this); }

        uiHandler.exit();
    }

    public void
    displayHomeScreen()
    {
        final String choice = uiHandler.displaySplashScreen();
        if (choice.equals(UIHandler.QUIT_OPTION)) { running = false; }
    }

    public void
    displayMainMenu()
    {
        final List<String> gameList = loadGameList();

        if (gameList.isEmpty())
        {
            running = false;
            uiHandler.displayError("Please ensure that the Cork JAR is in the same directory as the 'games' folder and that the folder is populated.");
        }
        else
        {
            final String choice = uiHandler.displayMainMenu(gameList);
            if (choice.equals(UIHandler.QUIT_OPTION)) { running = false; }

            gameName = choice;
        }
    }

    public void
    displayGameMenu()
    {
        try
        {
            currentGame = new Game(gameName);

            final String choice = uiHandler.displayGameMenu(gameName);
            if (choice.equals(UIHandler.QUIT_OPTION)) { running = false; }
            else if (choice.equals(UIHandler.LOAD_GAME_OPTION)) { loadGame(); }
        }
        catch (Exception e)
        {
            running = false;
            uiHandler.displayError("Error loading game. Exiting with message:\n\n" + e.getMessage() + "\n");
        }
    }

    private List<String>
    loadGameList()
    {
        try (Stream<Path> paths = Files.find(Paths.get(GAMES_DIRECTORY_PATH), 1, (path, attributes) -> attributes.isDirectory()))
        {
            List<String> gameList = paths.map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
            gameList.remove(0);

            return gameList;
        }
        catch (IOException e) { return Collections.emptyList(); }
    }

    private Path
    getSaveFilePath() { return Paths.get(String.format("%s/%s/%s", GAMES_DIRECTORY_PATH, gameName, SAVE_FILE_NAME)); }

    private void
    loadGame()
    {
        try
        {
            final List<String> decodedCommands = Files.readAllLines(getSaveFilePath())
                    .stream()
                    .map(c -> new String(
                            Base64.getDecoder()
                                    .decode(c.getBytes())))
                    .collect(Collectors.toList());

            for (final String input : decodedCommands) { currentGame.handleCommand(input); }
            uiHandler.print("Game loaded.");
        }
        catch (IOException e) { uiHandler.displayError("Game could not be loaded."); }
    }

    private void
    saveGame()
    {
        try
        {
            final List<String> encodedCommands = currentGame.getPreviousCommands()
                    .stream()
                    .map(c -> new String(
                            Base64.getEncoder()
                                    .encode(c.getBytes())))
                    .collect(Collectors.toList());

            Files.write(getSaveFilePath(), encodedCommands);
            uiHandler.print("Game saved.");
        }
        catch (IOException e) { uiHandler.displayError("Game could not be saved."); }
    }

    public void
    runGame()
    {
        uiHandler.clearScreen();
        uiHandler.print(currentGame.handleCommand("look"));

        while(!currentGame.isGameOver())
        {
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
                    running = false;
                    return;
                default:
                    String result = currentGame.handleCommand(userInput);
                    uiHandler.print(result);
                    break;
            }
        }

        uiHandler.clearScreen();
        if (currentGame.playerVictory()) { uiHandler.print("You won!"); }
        else { uiHandler.print("You died."); }
        uiHandler.print("\nPress 'ENTER' to continue.");
        uiHandler.getInput();
    }
}
