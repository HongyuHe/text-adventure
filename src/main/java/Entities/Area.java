package Entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Area implements IEntity {
    private String type;
    private String name;
    private Boolean active;
    private String description;
    private Set<String> inventory;
    private Set<String> npcs;
    private HashMap<String, String> connections;

    public Area(String type,
                String name,
                Boolean active,
                String description,
                Set<String> inventory,
                Set<String> npcs,
                HashMap<String, String> connections) {

        this.type = type;
        this.name = name;
        this.active = active;
        this.description = description;
        this.inventory = new HashSet<>(inventory);
        this.npcs = npcs;
        this.connections = connections;
    }

    public Boolean connectsTo(String nextArea) { return connections.containsKey(nextArea); }

    public String getConnection(String nextArea) { return connections.get(nextArea); }

    @Override
    public String getType() { return type; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Boolean isActive() { return active; }

    public Set<String> getInventory() { return inventory; }

    public Set<String> getNpcs() { return npcs; }

    public HashMap<String, String> getConnections() { return connections; }

}
