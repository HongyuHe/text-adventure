package Command;

import java.util.Set;

public class ChangeStat implements ICommand {
    private Set<String> args;

    public ChangeStat(Set<String> args) {
        this.args = args;
    }

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ object + " change " + args;
    }
}