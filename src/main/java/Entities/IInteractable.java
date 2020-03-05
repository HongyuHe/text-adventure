package Entities;
import Command.*;
import deserialiser.CommandBlueprint;

import java.util.Set;

public interface IInteractable {

    String getCurrentLocation();
    void setCurrentLocation(String newLocation);
    Set<CommandBlueprint> getCommands();
    Command findCommandOrElse(String cmd);
}
