package entity;

import booster.init.IEntity;
import booster.init.IInteractable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Obstacle implements IEntity, IInteractable {
    private String type;
    private String name;
    private Boolean active;
    private Boolean state;
    private String blocks;
    private String requiredObject;
    private Set<Command> commands;

    public Obstacle(String type,
                    String name,
                    Boolean active,
                    Boolean state,
                    String blocks,
                    String requiredObject,
                    ArrayList<Command> commands) {

        this.type = type;
        this.name = name;
        this.active = active;
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
    public Set<Command> getCommands() { return new HashSet<>(commands); }

    public Boolean getState() { return state; }

    public String getBlocks() { return blocks; }

    public String getRequiredObject() { return requiredObject; }

}

