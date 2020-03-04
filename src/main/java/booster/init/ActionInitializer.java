package booster.init;

import action.Action;
import action.ActionFactory;
import entity.Command;
import entity.Player;

import java.util.HashMap;

public class ActionInitializer {
    public static void PopulateAction(GameEntities entities) {
        HashMap<String, Action> actions = new HashMap<>();
        Player player = entities.getPlayer();

        for (Command cmd : player.getCommands()) {
            actions.putIfAbsent(cmd.getName(), ActionFactory.creatAction(cmd));
        }
        player.setActions(new HashMap<>(actions));
    }
}
