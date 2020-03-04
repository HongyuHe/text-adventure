package Command;

public final class CommandFactory {
    public static ICommand createCommand(Command cmd){
        switch (cmd.getFunction()) {
            case "ChangeLocation": return new ChangeLocation(cmd.getParams());
            case "ListContents": return new ListContents(cmd.getParams());
            case "ChangeStat": return new ChangeStat(cmd.getParams());
            case "Describe": return new Describe(cmd.getParams());
            default: return null;
        }
    }
}
