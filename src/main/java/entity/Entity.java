package entity;

import command.Command;
import deserialiser.CommandBlueprint;

import java.util.Set;

public abstract class Entity { // NOSONAR - Entity is intended to be used as an API, an interface is not appropriate here

    public abstract String getType();

    public abstract String getName();

    public abstract String getDescription();

    public abstract Boolean isActive();

    public abstract Command findCommandOrElse(String cmd);
}
