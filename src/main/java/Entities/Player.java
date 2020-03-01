package Entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player implements ICharacter, IInteractable {
    String type;
    String name;
    String desciption;
    Set<String> inventory;
    Boolean active;
    String currentLocation;
    Stat stat;
    Set<Command> commands;

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
        this.desciption = description;
        this.inventory = new HashSet<>(inventory);
        this.active = active;
        this.currentLocation = currentLocation;
        this.stat = stat;
        this.commands = new HashSet<>(commands);
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return desciption; }

    @Override
    public Boolean isActive() { return active; }

}

