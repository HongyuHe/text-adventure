package Entities;
import Command.*;
import deserialiser.CommandBlueprint;

import java.util.Set;

public interface IInteractable {

    Set<CommandBlueprint> getCommands();

    ICommand findCommandOrElse(String cmd);
}
