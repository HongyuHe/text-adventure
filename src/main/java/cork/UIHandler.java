package cork;

import java.awt.*;
import java.util.Arrays;
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

    private static final String DEFAULT_PROMPT   = ">";
    private static final String CLEAR_BOOKMARK   = "clear";
    private static final String QUIT_OPTION      = "Quit";
    private static final String CONTINUE_OPTION  = "Continue";
    private static final String NEW_GAME_OPTION  = "New Game";
    public  static final String LOAD_GAME_OPTION = "Load Game";

    private static final List<String> HOME_SCREEN_OPTIONS = Arrays.asList(CONTINUE_OPTION, QUIT_OPTION);
    private static final List<String> GAME_MENU_OPTIONS   = Arrays.asList(NEW_GAME_OPTION, LOAD_GAME_OPTION, QUIT_OPTION);

    private TextIO textIO = TextIoFactory.getTextIO();
    private TextTerminal<?> terminal = textIO.getTextTerminal();


    UIHandler()
    {
        terminal.setBookmark(CLEAR_BOOKMARK);
        terminal.getProperties().setPromptColor(Color.GREEN);
        terminal.getProperties().setInputColor(Color.WHITE);
    }
    
    public String displayHomeScreen() {
        clearScreen();
        print(CORK_LOGO);
        return textIO.newStringInputReader()
                .withNumberedPossibleValues(HOME_SCREEN_OPTIONS)
                        .read();
    }

    public String displayMainMenu(List<String> games) {
        clearScreen();
        games.add(QUIT_OPTION);
        return textIO.newStringInputReader()
                .withNumberedPossibleValues(games)
                        .read("Please select a game to play:");
    }

    public String displayGameMenu(String game) {
        clearScreen();
        print(game);
        return textIO.newStringInputReader()
                .withNumberedPossibleValues(GAME_MENU_OPTIONS)
                        .read();
    }

    public void exit() { terminal.dispose(); }

    public void clearScreen() { terminal.resetToBookmark(CLEAR_BOOKMARK); }

    public String getInput() { return textIO.newStringInputReader().withDefaultValue(DEFAULT_PROMPT).read().toLowerCase(); }

    public void print(String string) { terminal.println(string); }

    public boolean promptUser(String prompt) { return textIO.newBooleanInputReader().read(prompt); }
}
