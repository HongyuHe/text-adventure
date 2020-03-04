package action;

import java.util.Set;

public class ListContents implements Action {
    private Set<String> args;


    public ListContents(Set<String> args) {
        this.args = args;
    }

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ "List contents of " + args;
    }
}