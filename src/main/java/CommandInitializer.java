import Command.*;
import Entities.*;
import deserialiser.CommandBlueprint;

import java.util.HashMap;

public class CommandInitializer {
    public static void PopulateAction(GameEntities entities) {
        HashMap<String, ICommand> actions = new HashMap<>();
        Player player = entities.getPlayer();

        for (CommandBlueprint cmd : player.getCommandBlueprints()) {
            actions.putIfAbsent(cmd.getName(), CommandFactory.createCommand(cmd));
        }
        player.setCommands(new HashMap<>(actions));
    }
}
