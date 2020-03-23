package command;

import dictionary.GameEntities;
import entity.*;

public class Fight implements ICommand {
    final Entity parent;

    public
    Fight(final Entity parent) { this.parent = parent; }

    @Override
    public String
    apply(final String object, final GameEntities ge)
    {
        final String REDUCTION_STAT  = "health";
        final String COMPARISON_STAT = "strength";
        final Entity targetEntity = ge.getEntityOrDefault(object);

        if (targetEntity.getType().equals("NPC"))
        {
            final Npc opponent = ge.getNpcOrDefault(object);
            if (opponent.isFriendly()) { return String.format("%s is not hostile.", object); }
        }

        final Integer opponentStrength = targetEntity.getStatValue(COMPARISON_STAT);
        final Integer parentStrength   = parent.getStatValue(COMPARISON_STAT);

        if (parentStrength < opponentStrength)
        {
            parent.setStat(REDUCTION_STAT, parent.getStatValue(REDUCTION_STAT) - targetEntity.getStatValue(COMPARISON_STAT));
            return String.format("%s, - %d health", parent.getName(), targetEntity.getStatValue(COMPARISON_STAT));
        }
        else if (opponentStrength.equals(parentStrength))
        {
            parent.setStat(REDUCTION_STAT, parent.getStatValue(REDUCTION_STAT) - (targetEntity.getStatValue(COMPARISON_STAT) / 2));
            targetEntity.setStat(REDUCTION_STAT, 0);
            targetEntity.setActive(false);

            final Area location = ge.getAreaOrDefault(ge.getPlayer().getCurrentLocation());

            for (final String item : targetEntity.getInventory()) { location.addToInventory(item); }

            location.removeNpc(targetEntity.getName());

            return String.format("%s, -%d health%n%s is destroyed.", parent.getName(), (targetEntity.getStatValue(COMPARISON_STAT) / 2), object);
        }
        else
        {
            targetEntity.setStat(REDUCTION_STAT, 0);
            targetEntity.setActive(false);

            final Area location = ge.getAreaOrDefault(ge.getPlayer().getCurrentLocation());

            for (final String item : targetEntity.getInventory()) { location.addToInventory(item); }

            location.removeNpc(targetEntity.getName());

            return String.format("%s is destroyed.", object);
        }
    }
}
