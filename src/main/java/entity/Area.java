package entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Area extends Entity {
    private String type;
    private String name;
    private Boolean active;
    private String description;
    private Set<String> inventory;
    private Set<String> obstacles;
    private Set<String> npcs;
    private Map<String, String> connections;

    public Area(String type, // NOSONAR - 8 parameters are required to allow deserialization
                String name,
                Boolean active,
                String description,
                Set<String> inventory,
                Set<String> obstacles,
                Set<String> npcs,
                Map<String, String> connections) {

        this.type = type;
        this.name = name;
        this.active = active;
        this.description = description;
        this.inventory = new HashSet<>(inventory);
        this.obstacles = new HashSet<>(obstacles);
        this.npcs = npcs;
        this.connections = connections;
    }

    public boolean connectsTo(String nextArea) { return connections.containsKey(nextArea); }

    public String getConnection(String nextArea) { return connections.get(nextArea); }

    @Override
    public String getType() { return type; }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription()
    {
        StringBuilder d = new StringBuilder(description);

        if (!inventory.isEmpty()) { d.append("\nYou can see the following items here:\n"); }
        for (String item : inventory) { d.append(item).append(", "); }

        d.delete(d.length() - 2, d.length());

        return d.toString();
    }

    @Override
    public Boolean isActive() { return active; }

    public Set<String> getInventory() { return inventory; }

    public Set<String> getObstacles() { return obstacles; }

    public Set<String> getNpcs() { return npcs; }

    public Map<String, String> getConnections() { return connections; }

}
