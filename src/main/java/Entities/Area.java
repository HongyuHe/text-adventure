package Entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Area implements IEntity {
    String type;
    String name;
    Boolean active;
    String description;
    Set<String> inventory;
    Set<String> npcs;
    HashMap<String, String> connections;

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

}
