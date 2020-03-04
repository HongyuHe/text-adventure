import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine {
    List<Game> games;
    Game currentGame;

    private UIHandler UI = new UIHandler();
    private Game game = new Game();

    //    String handleGameover() {}

    List<String> loadGameList() {
        try (Stream<Path> walk = Files.walk(Paths.get(".\\savefiles"))) {
            return walk.map(Path::toString).filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    void startGame(game: Game) {}
//
//    void quit() {}
//
//    Boolean startNewGame() {
//        currentGame = new Game();
//    }

    Boolean loadSavedGame() { // TODO: implement
            System.out.println("Game loaded!\n");
            return true;
        }

    //    Game getSave() {}

    Boolean saveGame() { // TODO: implement
        System.out.println("Game saved!\n");
        return true;
    }

    void runGame() {
        while(true) {
            String userInput = UI.getInput();
            if(userInput.compareTo("save") == 0) {
                saveGame();
            } else if(userInput.compareTo("load") == 0) {
                loadSavedGame();
            } else if(userInput.compareTo("quit") == 0) {
                System.out.println("Quitting game...\n");
            } else {
                game.handleCommand(userInput);
            }
            System.out.println("Prev commands: " + game.getPreviousCommands().toString());
        }
    }
}
