package command;

import entity.Entity;
import deserialiser.CommandBlueprint;
import entity.Locatable;

public final class CommandFactory {
    private CommandFactory() {}

    public static Command createCommand(final CommandBlueprint cmd, final Entity parent) {
        switch (cmd.getFunction()) {
            case "ChangeLocation": return new ChangeLocation((Locatable) parent);
            case "ListContents":   return new ListContents(parent);
            case "ChangeStat":     return new ChangeStat(parent);
            case "ChangeState":    return new ChangeState(parent);
            case "Describe":       return new Describe(parent);
            case "TakeItem":       return new TakeItem((Locatable) parent);
            case "DropItem":       return new DropItem((Locatable) parent);
            case "ListStats":      return new ListStats(parent);
            default:               return new DefaultCommand();
        }
    }
}
