package command;

import entity.Entity;
import deserialiser.CommandBlueprint;
import entity.Locatable;
import entity.Obstacle;

public final class CommandFactory {
    private CommandFactory() {}

    public static ICommand createCommand(final CommandBlueprint cmd, final Entity parent) {
        switch (cmd.getFunction()) {
            case "ChangeLocation": return new ChangeLocation((Locatable) parent);
            case "ListContents":   return new ListContents(parent);
            case "ChangeStat":     return new ChangeStat(parent);
            case "ChangeState":    return new ChangeState((Obstacle) parent);
            case "Describe":       return new Describe(parent);
            case "TakeItem":       return new TakeItem(parent);
            case "DropItem":       return new DropItem(parent);
            case "ListStats":      return new ListStats(parent);
            case "Fight":          return new Fight(parent);
            default:               return new DefaultCommand(parent);
        }
    }
}
