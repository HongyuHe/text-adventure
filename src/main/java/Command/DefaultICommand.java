package Command;

public class DefaultICommand implements ICommand {

    public DefaultICommand(){};

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ "You can't do that";
    }
}