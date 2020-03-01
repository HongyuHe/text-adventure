package Entities;

import java.util.ArrayList;

public class Command {
    String name;
    String function;
    ArrayList<String> params;

    public Command(String name,
                    String function,
                    ArrayList<String> params) {

        this.name = name;
        this.function = function;
        this.params = params;
    }
}