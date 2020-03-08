package command;

import dictionary.GameEntities;
import entity.Area;
import entity.ICharacter;
import entity.IInteractable;

import java.util.Set;

public class TakeItem extends Command {
    private IInteractable parent;
    private Set<String> args;

    public TakeItem(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object, GameEntities ge) {
        if (!(parent instanceof ICharacter)) { return "You cannot do that."; }

        Area area = ge.getAreaOrElse(parent.getCurrentLocation());

        if (!area.hasInInventory(object)) { return String.format("You cannot pick up '%s'", object); }

        area.removeFromInventory(object);

        ((ICharacter) parent).addToInventory(object);

        return object + " -> " + parent.getName();
    }
}