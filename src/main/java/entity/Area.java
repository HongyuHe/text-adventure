package entity;

import deserialiser.CommandBlueprint;

import java.util.*;

public class Area extends Entity {
    private Set<String> obstacles;
    private Set<String> npcs;
    private Map<String, String> connections;

    public
    Area()
    {
        new Area(false, "", "", "",
                Collections.emptySet(), Collections.emptyMap(),
                Collections.emptySet(), Collections.emptySet(),
                Collections.emptySet(), Collections.emptyMap()
        );
    }

    public
    Area(final boolean active, // NOSONAR - Many parameters are required to allow deserialization
                final String type,
                final String name,
                final String description,
                final Set<String> inventory,
                final Map<String, Integer> stats,
                final Set<CommandBlueprint> commandBlueprints,
                final Set<String> obstacles,
                final Set<String> npcs,
                final Map<String, String> connections)
    {
        super(active, type, name, description, inventory, stats, commandBlueprints);
        this.obstacles = new HashSet<>(obstacles);
        this.npcs = npcs;
        this.connections = connections;
    }

    public boolean
    connectsTo(final String nextArea) { return connections.containsKey(nextArea); }

    public String
    getConnection(final String nextArea) { return connections.get(nextArea); }

    @Override
    public String
    getDescription()
    {
        return description + buildOutputList("people", npcs) + buildOutputList("items", inventory);
    }

    private String
    buildOutputList(final String noun, Collection<String> c)
    {
        final String DELIMITER = ", ";

        if (c.isEmpty()) { return ""; }

        StringBuilder sb = new StringBuilder(String.format("%nYou can see the following %s here:%n", noun));
        for (final String e : c) { sb.append(e).append(DELIMITER); }

        return sb.substring(0, sb.length() - DELIMITER.length());
    }

    public Set<String>
    getObstacles() { return new HashSet<>(obstacles); }

    public void
    removeObstacle(final String obstacle) { obstacles.remove(obstacle); }

    public void
    removeNpc(final String npc) { npcs.remove(npc); }
}
