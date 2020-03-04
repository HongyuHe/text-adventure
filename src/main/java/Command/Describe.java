package Command;

import java.util.Set;

public class Describe implements ICommand {
    private Set<String> args;

    public Describe(Set<String> args) {
        this.args = args;
    }

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ "Describe " + args;
    }
}