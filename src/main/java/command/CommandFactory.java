package command;

import entity.IInteractable;
import deserialiser.CommandBlueprint;

public final class CommandFactory {
    public static Command createCommand(CommandBlueprint cmd, IInteractable parent) {
        switch (cmd.getFunction()) {
            case "ChangeLocation": return new ChangeLocation(cmd.getParams(), parent);
            case "ListContents": return new ListContents(cmd.getParams(), parent);
            case "ChangeStat": return new ChangeStat(cmd.getParams(), parent);
            case "ChangeState": return new ChangeState(cmd.getParams(), parent);
            case "Describe": return new Describe(cmd.getParams(), parent);
            default: return null;
        }
    }
}
