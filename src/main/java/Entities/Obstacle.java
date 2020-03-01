package Entities;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Obstacle implements IInteractable {
    String type;
    String name;
    Boolean active;
    Boolean state;
    String blocks;
    String requiredObject;
    ArrayList<Commands> commands;

    public Obstacle(String type,
                    String name,
                    Boolean active,
                    Boolean state,
                    String blocks,
                    String requiredObject,
                    ArrayList<Commands> commands) {

        this.type = type;
        this.name = name;
        this.active = active;
        this.state = state;
        this.blocks = blocks;
        this.requiredObject = requiredObject;
        this.commands = commands;
    }

    public static class Commands {
        public String name;
        public String function;
        public ArrayList<String> params;

        public Commands(String name,
                        String function,
                        ArrayList<String> params) {

            this.name = name;
            this.function = function;
            this.params = params;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return "This entity does not have a description, quite the obstacle";
    }

}
