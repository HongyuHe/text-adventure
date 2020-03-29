package command;

import dictionary.GameEntities;
import entity.*;

public class Describe implements ICommand {
    @Override
    public String apply(String object, final GameEntities ge) {
        if (object.equals("")) { object = ge.getPlayer().getCurrentLocation(); }

        final Entity e = ge.getEntityOrDefault(object);
        final Area localArea = ge.getAreaOrDefault(ge.getPlayer().getCurrentLocation());

        if (!e.isActive()
                || !(ge.getPlayer().hasInInventory(object)
                    || (object.equals(ge.getPlayer().getName()))
                    || localArea.hasInInventory(object)
                    || localArea.hasNpc(object)
                    || localArea.hasObstacle(object)
                    || (ge.getAreaEntities().containsKey(object)
                        && localArea.getName().equals(object)))) { return String.format("You cannot see '%s'.", object); }

        return e.getDescription();
    }
}