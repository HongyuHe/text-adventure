package Command;

import Dictionary.GameEntities;

public class DefaultICommand implements ICommand {

    public DefaultICommand(){};

    @Override
    public String apply(String object, GameEntities ge) {
        return "Action>>>$ "+ "You can't do that";
    }
}