package action;

import java.util.Set;

public class DefaultAction implements Action {

    public DefaultAction(){};

    @Override
    public String apply(String object) {
        return "Action>>>$ "+ "You can't do that";
    }
}