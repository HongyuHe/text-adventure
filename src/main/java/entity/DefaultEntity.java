package entity;

import deserialiser.CommandBlueprint;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class DefaultEntity extends Locatable {
    private static final DefaultEntity DEFAULT_ENTITY_INSTANCE =
            new DefaultEntity(true, "DefaultEntity", "DefaultEntity", "You cannot find that here.",
                    Collections.emptySet(), Collections.emptyMap(), Collections.emptySet(), "");

    private DefaultEntity(final boolean active, // NOSONAR - many parameters required for deserialization
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

    public static DefaultEntity instance() { return DEFAULT_ENTITY_INSTANCE; }

    @Override
    public void
    setActive(final boolean value) { /* Do nothing */ }

    @Override
    public void
    addToInventory(final String object) { /* Do nothing */ }

    @Override
    public void
    removeFromInventory(final String object) { /* Do nothing */ }

    @Override
    public void
    setStat(final String name, final Integer value) { /* Do nothing */ }

    @Override
    public void
    setCurrentLocation(final String newLocation) { /* Do nothing */ }
}
