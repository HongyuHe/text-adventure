package entity;

import deserialiser.CommandBlueprint;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public abstract class Locatable extends Entity {
    private String currentLocation;

    public Locatable()
    {
        super(false, "", "", "",
                Collections.emptySet(), Collections.emptyMap(), Collections.emptySet());
        this.currentLocation = "";
    }

    public
    Locatable(final boolean active, // NOSONAR - Many parameters are required to allow deserialization
              final String type,
              final String name,
              final String description,
              final Set<String> inventory,
              final Map<String, Integer> stats,
              final Set<CommandBlueprint> commandBlueprints,
              final String currentLocation)
    {
        super(active, type, name, description, inventory, stats, commandBlueprints);
        this.currentLocation = currentLocation;
    }

    public void
    setCurrentLocation(String newLocation) { currentLocation = newLocation; }

    public String
    getCurrentLocation() { return currentLocation; }
}
