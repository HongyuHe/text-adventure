package Entities;
import Command.*;
import deserialiser.CommandBlueprint;

import java.util.Set;

public interface IInteractable {

    Set<CommandBlueprint> getCommandBlueprints();

    ICommand findCommandOrElse(String cmd);
}
