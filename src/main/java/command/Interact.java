package command;

import dictionary.GameEntities;
import entity.Entity;
import entity.IInteractable;
import entity.Npc;

import java.util.Set;

public class Interact extends Command {
    private IInteractable parent; // NOSONAR
    private Set<String> args; // NOSONAR

    public Interact(Set<String> args, IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(String object, GameEntities ge) {
        Entity targetEntity = ge.findEntityOrElse(object);
        String output = "";
        if(targetEntity.getType().equals("NPC")) {
            output = ((Npc) targetEntity).getInteraction();
        }
        else {
            output = targetEntity.getDescription();
        }

        return output;
    }


}