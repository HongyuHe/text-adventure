package command;

import dictionary.GameEntities;
import entity.Area;
import entity.ICharacter;
import entity.IInteractable;

import java.util.Set;

public class DropItem extends Command {
    private IInteractable parent;
    private Set<String> args;

    public DropItem(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object, GameEntities ge) {
        if (!(parent instanceof ICharacter)) { return "You cannot do that."; }

        ICharacter p = (ICharacter) parent;

        if (!p.hasInInventory(object)) { return String.format("You are not holding '%s'", object); }
        p.removeFromInventory(object);

        Area area = ge.getArea(parent.getCurrentLocation());

        area.addToInventory(object);

        return object + " -> " + area.getName();
    }
}