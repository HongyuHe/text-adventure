package cork;

import java.util.Arrays;
import java.util.List;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class UIHandler {
    private TextIO textIO = TextIoFactory.getTextIO();
    private TextTerminal terminal = textIO.getTextTerminal();

    String displayMainMenu() {
        terminal.setBookmark("x");
        terminal.getProperties().setPromptColor("white");
        return textIO.newStringInputReader().withNumberedPossibleValues("Select Game", "Quit").read("Welcome to Cork");
    }

    String displayGamesMenu(List<String> games) {
        clearScreen();
        games.add("Quit");
        return textIO.newStringInputReader().withNumberedPossibleValues(games).read("Select a game");
    }

    String displayGameSubMenu(String game) {
        clearScreen();
        terminal.printf("You chose %s\n", game);
        List<String> options = Arrays.asList("New Game", "Load Game", "Quit");
        return textIO.newStringInputReader().withNumberedPossibleValues(options).read("Select an option");
    }

    void clearScreen() { terminal.resetToBookmark("x"); }

    String getInput() { return textIO.newStringInputReader().read().toLowerCase(); }

    void print(String string) { terminal.println(string); }

    Boolean promptUser(String prompt) { return textIO.newBooleanInputReader().read(prompt); }
}
