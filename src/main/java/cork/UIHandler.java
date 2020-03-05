package cork;

import java.util.List;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class UIHandler {
    private static final String CORK_LOGO =
            " __        __   _                            _        \n" +
            " \\ \\      / ___| | ___ ___  _ __ ___   ___  | |_ ___  \n" +
            "  \\ \\ /\\ / / _ | |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\ \n" +
            "   \\ V  V |  __| | (_| (_) | | | | | |  __/ | || (_) |\n" +
            "    \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/ \n" +
            "\n" +
            "             ██████╗ ██████╗ ██████╗ ██╗  ██╗\n" +
            "            ██╔════╝██╔═══██╗██╔══██╗██║ ██╔╝\n" +
            "            ██║     ██║   ██║██████╔╝█████╔╝ \n" +
            "            ██║     ██║   ██║██╔══██╗██╔═██╗ \n" +
            "            ╚██████╗╚██████╔╝██║  ██║██║  ██╗\n" +
            "             ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝\n" +
            "\n";

    private static final String CLEAR_SCREEN = "clear";

    private TextIO textIO = TextIoFactory.getTextIO();
    private TextTerminal<?> terminal = textIO.getTextTerminal();


    UIHandler()
    {
        terminal.setBookmark(CLEAR_SCREEN);
        terminal.getProperties().setPromptColor("white");
    }

    public void exit() { terminal.dispose(); }

    public String displayHomeScreen() {
        clearScreen();
        print(CORK_LOGO);
        return textIO.newStringInputReader()
                .withNumberedPossibleValues("Select Game", "Quit")
                    .read("Please make your choice:");
    }

    public String displayMainMenu(List<String> games) {
        clearScreen();
        games.add("Quit");
        return textIO.newStringInputReader()
                .withNumberedPossibleValues(games)
                    .read("Select a game");
    }

    public String displayGameMenu(String game) {
        clearScreen();
        print(game + "\n");
        return textIO.newStringInputReader()
                .withNumberedPossibleValues("New Game", "Load Game", "Quit")
                    .read("Select an option");
    }

    public void clearScreen() { terminal.resetToBookmark(CLEAR_SCREEN); }

    public String getInput() { return textIO.newStringInputReader().withDefaultValue(">").read().toLowerCase(); }

    public void print(String string) { terminal.println(string); }

    public boolean promptUser(String prompt) { return textIO.newBooleanInputReader().read(prompt); }
}
