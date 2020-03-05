import java.awt.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.beryx.textio.StringInputReader;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class UIHandler {
    private Scanner scanner = new Scanner(System.in);
    private TextIO textIO = TextIoFactory.getTextIO();
    private TextTerminal terminal = textIO.getTextTerminal();

    Boolean displayMainMenu() {
        terminal.setBookmark("x");
        terminal.getProperties().setPromptColor("yellow");
        return textIO.newBooleanInputReader().read("Press to continue:");
    }

    String displayGamesMenu(List<String> games) {
        terminal.resetToBookmark("x");
        games.add("quit");
        return textIO.newStringInputReader().withNumberedPossibleValues(games).read("Select a game");
    } // TODO: Display list of games + quit()

    void displayGameSubMenu(String game) {
        terminal.resetToBookmark("x");
        terminal.printf("You chose %s\n", game);
        List<String> options = Arrays.asList("newgame", "save", "load");
        textIO.newStringInputReader().withNumberedPossibleValues(options).read("Select an option");
    } // TODO: Display, newgame, save, load, quit()

    String getInput() { return scanner.nextLine(); }

//    void clearScreen() {}

//    void printResult(String result) {}
}
