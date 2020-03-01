package Entities;

import java.util.ArrayList;

public class Npc implements ICharacter {
    String type;
    String name;
    String description;
    ArrayList<String> inventory;
    Boolean active;
    Stat stat;

    public Npc(String type,
               String name,
               String description,
               ArrayList<String> inventory,
               Boolean active,
               Stat stat) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
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
