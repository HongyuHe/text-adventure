package command;

import entity.ICharacter;
import entity.IInteractable;
import deserialiser.CommandBlueprint;

public final class CommandFactory {
    private CommandFactory() {}

    public static Command createCommand(final CommandBlueprint cmd, final IInteractable parent) {
        switch (cmd.getFunction()) {
            case "ChangeLocation": return new ChangeLocation(cmd.getParams(), parent);
            case "ListContents":   return new ListContents(cmd.getParams(),   parent);
            case "ChangeStat":     return new ChangeStat(cmd.getParams(),     parent);
            case "ChangeState":    return new ChangeState(cmd.getParams(),    parent);
            case "Describe":       return new Describe(cmd.getParams(),       parent);
            case "TakeItem":       return new TakeItem(cmd.getParams(),       parent);
            case "DropItem":       return new DropItem(cmd.getParams(),       parent);
            case "ListStats":      return new ListStats(cmd.getParams(),      parent);
            default:               return new DefaultCommand();
        }
    }
}
