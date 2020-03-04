package Command;

import java.util.Set;

public class ListContents implements ICommand {
    private Set<String> args;


    public ListContents(Set<String> args) {
        this.args = args;
    }

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ "List contents of " + args;
    }
}