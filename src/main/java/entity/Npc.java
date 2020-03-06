package entity;

import command.Command;
import command.DefaultCommand;

import java.util.HashSet;
import java.util.Set;

public class Npc extends Character {
    private String type;
    private String name;
    private String description;
    private String interaction;
    private Set<String> inventory;
    private Boolean active;
    private Boolean isFriendly;
    private Stat stat;

    public Npc(String type,
               String name,
               String description,
               String interaction,
               Set<String> inventory,
               Boolean active,
               Boolean isFriendly,
               Stat stat) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.interaction = interaction;
        this.inventory = new HashSet<>(inventory);
        this.active = active;
        this.isFriendly = isFriendly;
        this.stat = stat;
    }

    @Override
    public String getType() { return type; }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    public String getInteraction() { return interaction; }

    @Override
    public Boolean isActive() { return active; }

    public Boolean getIsFriendly() { return isFriendly; }

    @Override
    public Set<String> getInventory() { return new HashSet<>(inventory); }

    public Stat getStat() { return stat; }

    @Override
    public Command findCommandOrElse(String cmd) {
        return new DefaultCommand();
    }
}
