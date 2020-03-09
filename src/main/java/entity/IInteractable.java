package entity;
import command.*;
import deserialiser.CommandBlueprint;

import java.util.Set;

public interface IInteractable {
    
    String getName();
    String getCurrentLocation();
    void setCurrentLocation(String newLocation);
    Set<CommandBlueprint> getCommands();
    Command findCommandOrElse(String cmd);
}
