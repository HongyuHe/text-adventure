package Entities;

import java.util.ArrayList;

public class Obstacle implements IEntity, IInteractable {
    String type;
    String name;
    Boolean active;
    Boolean state;
    String blocks;
    String requiredObject;
    ArrayList<Command> commands;

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
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return "This entity does not have a description, quite the obstacle";
    }

}
