package entity;

import deserialiser.CommandBlueprint;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Npc extends Entity {

    private boolean isFriendly;

    public
    Npc()
    {
        this(false, "", "", "",
                Collections.emptySet(), Collections.emptyMap(), Collections.emptySet(),
                true);
    }

    public
    Npc(final boolean active,
               final String type,
               final String name,
               final String description,
               final Set<String> inventory,
               final Map<String, Integer> stats,
               final Set<CommandBlueprint> commandBlueprints,
               final boolean isFriendly)
    {
        super(active, type, name, description, inventory, stats, commandBlueprints);
        this.isFriendly = isFriendly;
    }

    public boolean
    isFriendly() { return isFriendly; }
}
