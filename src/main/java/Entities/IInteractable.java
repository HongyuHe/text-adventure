package Entities;
import Command.*;

import java.util.Set;

public interface IInteractable {

    Set<Command> getCommands();

    ICommand findCommandOrElse(String cmd);
}
