package Entities;

import java.util.ArrayList;

public class Player implements ICharacter, IInteractable {
    String type;
    String name;
    String desciption;
    ArrayList<String> inventory;
    Boolean active;
    String currentLocation;
    Stat stat;
    ArrayList<Command> commands;

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
        this.inventory = inventory;
        this.active = active;
        this.currentLocation = currentLocation;
        this.stat = stat;
        this.commands = commands;
    }
}

