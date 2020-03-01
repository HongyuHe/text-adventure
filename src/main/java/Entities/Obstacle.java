package Entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Obstacle implements IEntity, IInteractable {
    String type;
    String name;
    Boolean active;
    Boolean state;
    String blocks;
    String requiredObject;
    Set<Command> commands;

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
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "This entity does not have a description, quite the obstacle";
    }

    @Override
    public Boolean isActive() { return active; }
}
