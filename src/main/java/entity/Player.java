package entity;

import deserialiser.CommandBlueprint;

import java.util.*;

public class Player extends Locatable {
    public
    Player(final boolean active, // NOSONAR - Many parameters are required to allow deserialization
                  final String type,
                  final String name,
                  final String description,
                  final Set<String> inventory,
                  final Map<String, Integer> stats,
                  final Set<CommandBlueprint> commandBlueprints,
                  final String currentLocation)
    {
        super(active, type, name, description, inventory, stats, commandBlueprints, currentLocation);
    }

    public boolean
    isDead() { return (stats.getOrDefault("health", 0) <= 0); }
}

