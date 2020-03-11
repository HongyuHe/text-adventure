package entity;

import deserialiser.CommandBlueprint;

import java.util.Map;
import java.util.Set;

public class Npc extends Entity {

    public Npc(final boolean active,
               final String type,
               final String name,
               final String description,
               final Set<String> inventory,
               final Map<String, Integer> stats,
               final Set<CommandBlueprint> commandBlueprints)
    {
        super(active, type, name, description, inventory, stats, commandBlueprints);
    }
}
