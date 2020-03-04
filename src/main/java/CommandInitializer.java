import Command.*;
import Entities.*;

import java.util.HashMap;

public class CommandInitializer {
    public static void PopulateAction(GameEntities entities) {
        HashMap<String, ICommand> actions = new HashMap<>();
        Player player = entities.getPlayer();

        for (Command cmd : player.getCommands()) {
            actions.putIfAbsent(cmd.getName(), CommandFactory.createCommand(cmd));
        }
        player.setActions(new HashMap<>(actions));
    }
}
