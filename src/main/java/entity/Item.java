package entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Item extends Entity {
    private String type;
    private String name;
    private String description;
    private Boolean consumable;
    private Boolean active;
    private Map<String, Integer> stats;

    public Item() { new Item("", "", "", false, false, Collections.emptyMap()); }

    public Item(String type,
                String name,
                String description,
                Boolean consumable,
                Boolean active,
                Map<String, Integer> stats) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.consumable = consumable;
        this.active = active;
        this.stats = stats;
    }

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
    public boolean isActive() { return active; }

    @Override
    public void setActive(final boolean value) { active = value; }

    public boolean isConsumable() { return consumable; }

    public Map<String, Integer> getStats() { return new HashMap<>(stats); }

    public Integer getStatValue(final String name) { return stats.getOrDefault(name, 0); }

}


