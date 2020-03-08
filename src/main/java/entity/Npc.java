package entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Npc extends Entity implements ICharacter {
    private String type;
    private String name;
    private String description;
    private Set<String> inventory;
    private Boolean active;
    private Map<String, Integer> stats;

    public Npc(String type,
               String name,
               String description,
               Set<String> inventory,
               Boolean active,
               Map<String, Integer> stats) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.inventory = new HashSet<>(inventory);
        this.active = active;
        this.stats = stats;
    }

    @Override
    public String getType() { return type; }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public boolean isActive() { return active; }

    @Override
    public void setActive(boolean value) { active = value; }

    @Override
    public Set<String> getInventory() { return new HashSet<>(inventory); }

    @Override
    public void addToInventory(String object) { inventory.add(object); }

    @Override
    public void removeFromInventory(String object) { inventory.remove(object); }

    @Override
    public boolean hasInInventory(String object) { return inventory.contains(object); }

    @Override
    public Map<String, Integer> getStats() { return stats; }

    @Override
    public void setStat(String name, Integer value) { stats.replace(name, value); }

    @Override
    public Integer getStatValue(String name) { return stats.getOrDefault(name, 0); }
}
