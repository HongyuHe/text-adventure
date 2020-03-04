package deserialiser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CommandBlueprint {
    private String name;
    private String function;
    private Set<String> params;

    public CommandBlueprint(String name,
                            String function,
                            ArrayList<String> params) {

        this.name = name;
        this.function = function;
        this.params = new HashSet<>(params);
    }

    public String getName() { return name; }

    public String getFunction() { return function; }

    public Set<String> getParams() { return new HashSet<>(params); }

}