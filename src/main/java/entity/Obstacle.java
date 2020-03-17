package entity;

import deserialiser.CommandBlueprint;

import java.util.*;

public class Obstacle extends Locatable {
    private boolean state;
    private String blocks;
    private String requiredObject;
    private String message;

    public
    Obstacle()
    {
        new Obstacle(false, "", "", "",
                Collections.emptySet(), Collections.emptyMap(),
                Collections.emptySet(), "", false, "", "", ""
        );
    }

    public
    Obstacle(final boolean active, // NOSONAR - Many parameters are required to allow deserialization
             final String type,
             final String name,
             final String description,
             final Set<String> inventory,
             final Map<String, Integer> stats,
             final Set<CommandBlueprint> commandBlueprints,
             final String currentLocation,
             final boolean state,
             final String blocks,
             final String requiredObject,
             final String message)
    {
        super(active, type, name, description, inventory, stats, commandBlueprints, currentLocation);
        this.state = state;
        this.blocks = blocks;
        this.requiredObject = requiredObject;
        this.message = message;
    }

    public void
    setState(final boolean state) { this.state = state; }

    public boolean
    getState() { return state; }

    public String
    getBlocks() { return blocks; }

    public String
    getRequiredObject() { return requiredObject; }

    public String
    getMessage() { return message; }
}

