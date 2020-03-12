package entity;

import command.Command;
import command.DefaultCommand;
import deserialiser.CommandBlueprint;

import java.util.*;

public abstract class Entity {
    protected boolean active;
    protected final String type;
    protected final String name;
    protected final String description;
    protected final Set<String> inventory;
    protected final Map<String, Integer> stats;
    private Map<String, Command> commands;

    private final Set<CommandBlueprint> commandBlueprints;

    public
    Entity()
    {
        this.active = false;
        this.type = "";
        this.name = "";
        this.description = "";
        this.inventory = Collections.emptySet();
        this.stats = Collections.emptyMap();
        this.commandBlueprints = Collections.emptySet();
    }

    public
    Entity(final boolean active,
           final String type,
           final String name,
           final String description,
           final Set<String> inventory,
           final Map<String, Integer> stats,
           final Set<CommandBlueprint> commandBlueprints)
    {
        this.active = active;
        this.type = type;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.stats = stats;
        this.commandBlueprints = commandBlueprints;
    }

    public boolean
    isActive() { return active; }

    public void
    setActive(final boolean value) { active = value; }

    public String
    getType() {  return type; }

    public String
    getName() { return name; }

    public String
    getDescription() { return description; }

    public Set<String>
    getInventory() { return new HashSet<>(inventory); }

    public boolean
    hasInInventory(final String object) { return inventory.contains(object); }

    public void
    addToInventory(final String object) { inventory.add(object); }

    public void
    removeFromInventory(final String object) { inventory.remove(object); }

    public Map<String, Integer>
    getStats() { return new HashMap<>(stats); }

    public Integer
    getStatValue(final String name) { return stats.getOrDefault(name, 0); }

    public void
    setStat(final String name, final Integer value) { stats.replace(name, value); }

    public Command
    findCommandOrElse(final String cmd) { return commands.getOrDefault(cmd, new DefaultCommand()); }

    public Set<CommandBlueprint>
    getCommandBlueprints() { return new HashSet<>(commandBlueprints); }

    public void
    setActions(final Map<String, Command> commands) { this.commands = new HashMap<>(commands); }
}
