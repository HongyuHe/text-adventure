package entity;

import command.*;
import deserialiser.CommandBlueprint;

import java.util.*;

public class Obstacle extends Entity implements IInteractable {
    private String type;
    private String name;
    private Boolean active;
    private String currentLocation;
    private Boolean state;
    private String blocks;
    private String requiredObject;
    private Set<CommandBlueprint> commands;

    private Map<String, Command> actions;

    public Obstacle(String type,
                    String name,
                    Boolean active,
                    String currentLocation,
                    Boolean state,
                    String blocks,
                    String requiredObject,
                    List<CommandBlueprint> commands) {

        this.type = type;
        this.name = name;
        this.active = active;
        this.currentLocation = currentLocation;
        this.state = state;
        this.blocks = blocks;
        this.requiredObject = requiredObject;
        this.commands = new HashSet<>(commands);
    }

    @Override
    public String getType() { return type; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "This entity does not have a description, quite the obstacle";
    }

    @Override
    public Boolean isActive() { return active; }

    @Override
    public Set<CommandBlueprint> getCommands() { return new HashSet<>(commands); }

    @Override
    public void setCurrentLocation(String newLocation) {
        currentLocation = newLocation;
    }

    @Override
    public String getCurrentLocation() { return currentLocation; }

    public Boolean getState() { return state; }

    public String getBlocks() { return blocks; }

    public String getRequiredObject() { return requiredObject; }

    public void setActions(Map<String, Command> actions) {
        this.actions = actions;
    }

    @Override
    public Command findCommandOrElse(String cmd) {
        return actions.getOrDefault(cmd, new DefaultCommand());
    }
}

