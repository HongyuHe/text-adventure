package action;

import java.util.Set;

public class ChangeLocation implements Action {
    private Set<String> args;

    public ChangeLocation(Set<String> args) {
        this.args = args;
    }

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ args + " change location to " + object;
    }
}
