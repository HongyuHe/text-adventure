package Entities;

import Command.*;
import deserialiser.CommandBlueprint;

import java.util.ArrayList;
import java.util.HashMap;
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
    private Set<CommandBlueprint> commandBlueprints;

    private HashMap<String, ICommand> commands;

    public Player(String type,
                  String name,
                  String description,
                  ArrayList<String> inventory,
                  Boolean active,
                  String currentLocation,
                  Stat stat,
                  ArrayList<CommandBlueprint> commandBlueprints) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.inventory = new HashSet<>(inventory);
        this.active = active;
        this.currentLocation = currentLocation;
        this.stat = stat;
        this.commandBlueprints = new HashSet<>(commandBlueprints);
        this.commands = null;
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
    public Set<String> getInventory() { return new HashSet<>(inventory); }

    @Override
    public Set<CommandBlueprint> getCommandBlueprints() { return new HashSet<>(commandBlueprints); }

    public String getCurrentLocation() { return currentLocation; }

    public Stat getStat() { return stat; }

    public void setCommands(HashMap<String, ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public ICommand findCommandOrElse(String cmd) {
        return commands.getOrDefault(cmd, new DefaultICommand());
    }
}

