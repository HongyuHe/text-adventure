package Entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Npc implements ICharacter {
    String type;
    String name;
    String description;
    Set<String> inventory;
    Boolean active;
    Stat stat;

    public Npc(String type,
               String name,
               String description,
               Set<String> inventory,
               Boolean active,
               Stat stat) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.inventory = new HashSet<>(inventory);
        this.active = active;
        this.stat = stat;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Boolean isActive() { return active; }
}
