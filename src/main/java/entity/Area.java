package entity;

import java.util.*;

public class Area extends Entity {
    private String type;
    private String name;
    private Boolean active;
    private String description;
    private Set<String> inventory;
    private Set<String> obstacles;
    private Set<String> npcs;
    private Map<String, String> connections;

    public Area()
    {
        new Area("", "", false, "",
            Collections.emptySet(), Collections.emptySet(),
            Collections.emptySet(), Collections.emptyMap()
        );
    }

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

        if (!npcs.isEmpty()) { d.append("\nYou can see the following people here:\n"); }
        for (final String npc : npcs) { d.append(npc).append(", "); }
        if (!npcs.isEmpty()) { d.delete(d.length() - 2, d.length()); }

        if (!inventory.isEmpty()) { d.append("\nYou can see the following items here:\n"); }
        for (final String item : inventory) { d.append(item).append(", "); }
        if (!inventory.isEmpty()) { d.delete(d.length() - 2, d.length()); }

        return d.toString();
    }

    @Override
    public boolean isActive() { return active; }

    @Override
    public void setActive(boolean value) { active = value; }

    public Set<String> getInventory() { return new HashSet<>(inventory); }

    public boolean hasInInventory(final String object) { return inventory.contains(object); }

    public void removeFromInventory(final String object) { inventory.remove(object); }

    public void addToInventory(final String object) { inventory.add(object); }

    public Set<String> getObstacles() { return new HashSet<>(obstacles); }

    public Set<String> getNpcs() { return npcs; }

    public Map<String, String> getConnections() { return new HashMap<>(connections); }

}
