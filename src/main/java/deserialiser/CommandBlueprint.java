package deserialiser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandBlueprint {
    private final String name;
    private final String function;

    public CommandBlueprint(String name,
                            String function)
    {
        this.name = name;
        this.function = function;
    }

    public String
    getName() { return name; }

    public String
    getFunction() { return function; }
}