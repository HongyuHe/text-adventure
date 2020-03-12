package command;

import dictionary.GameEntities;
import entity.*;

public class Fight implements Command {
    final Entity parent;

    public
    Fight(final Entity parent) { this.parent = parent; }

    @Override
    public String
    apply(final String object, final GameEntities ge)
    {
        final String REDUCTION_STAT  = "health";
        final String COMPARISON_STAT = "strength";

        final Entity opponent = ge.findEntityOrElse(object);

        final Integer opponentStrength = opponent.getStatValue(COMPARISON_STAT);
        final Integer parentStrength   = parent.getStatValue(COMPARISON_STAT);

        if (parentStrength < opponentStrength)
        {
            parent.setStat(REDUCTION_STAT, parent.getStatValue(REDUCTION_STAT) - 10);
            return String.format("%s, -10 health", parent.getName());
        }
        else if (opponentStrength.equals(parentStrength))
        {
            parent.setStat(REDUCTION_STAT, parent.getStatValue(REDUCTION_STAT) - 5);
            opponent.setStat(REDUCTION_STAT, 0);
            opponent.setActive(false);
            return String.format("%s, -5 health%n%s is killed.", parent.getName(), object);
        }
        else
        {
            opponent.setStat(REDUCTION_STAT, 0);
            opponent.setActive(false);
            return String.format("%s is killed.", object);
        }
    }

    @Override
    public String
    apply(Area object, GameEntities ge) { return ""; }

    @Override
    public String
    apply(Item object, GameEntities ge) { return ""; }

    @Override
    public String
    apply(Npc object, GameEntities ge) { return ""; }

    @Override
    public String
    apply(Player object, GameEntities ge) { return ""; }

    @Override
    public String
    apply(Obstacle object, GameEntities ge) { return ""; }
}
