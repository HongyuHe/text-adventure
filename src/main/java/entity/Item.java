package entity;

import deserialiser.CommandBlueprint;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Item extends Entity {
    private Boolean consumable;

    public
    Item()
    {
        new Item(false, "", "", "",
                Collections.emptySet(), Collections.emptyMap(),
                Collections.emptySet(), false
        );
    }

    public
    Item(final boolean active, // NOSONAR - Many parameters are required to allow deserialization
         final String type,
         final String name,
         final String description,
         final Set<String> inventory,
         final Map<String, Integer> stats,
         final Set<CommandBlueprint> commandBlueprints,
         final boolean consumable)
    {
        super(active, type, name, description, inventory, stats, commandBlueprints);
        this.consumable = consumable;
    }

    public boolean
    isConsumable() { return consumable; }
}


