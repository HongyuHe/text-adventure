package Entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player implements ICharacter, IInteractable {
    private String type;
    private String name;
    private String description;
    private Set<String> inventory;
    private Boolean active;
    private String currentLocation;
    private Stat stat;
    private Set<Command> commands;

    public Player(String type,
                  String name,
                  String description,
                  ArrayList<String> inventory,
                  Boolean active,
                  String currentLocation,
                  Stat stat,
                  ArrayList<Command> commands) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.inventory = new HashSet<>(inventory);
        this.active = active;
        this.currentLocation = currentLocation;
        this.stat = stat;
        this.commands = new HashSet<>(commands);
    }

    @Override
    public String getType() { return type; }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public Boolean isActive() { return active; }

    @Override
    public Set<String> getInventory() { return inventory; }

    @Override
    public Set<Command> getCommands() { return commands; }

    public String getCurrentLocation() { return currentLocation; }

    public Stat getStat() { return stat; }
}

