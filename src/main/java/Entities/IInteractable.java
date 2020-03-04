package Entities;
import Command.*;
import deserialiser.CommandBlueprint;

import java.util.Set;

public interface IInteractable extends IEntity {

    String getCurrentLocation();
    void setCurrentLocation(String newLocation);
    Set<CommandBlueprint> getCommands();
    ICommand findCommandOrElse(String cmd);
}
