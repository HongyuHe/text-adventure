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

            StringBuilder result = new StringBuilder(String.format("%s, -%d health%n%s is destroyed.", parent.getName(), (targetEntity.getStatValue(COMPARISON_STAT) / 2), object));

            final Area location = ge.getAreaOrDefault(ge.getPlayer().getCurrentLocation());
            for (final String item : targetEntity.getInventory())
            {
                location.addToInventory(item);
                result.append("\n").append(object).append(" drops ").append(item);
            }

            targetEntity.setActive(false);
            removeEntity(targetEntity, ge);

            return result.toString();
        }
        else if (parentStrength.compareTo(opponentStrength) > 0)
        {
            targetEntity.setStat(REDUCTION_STAT, 0);
            targetEntity.setActive(false);

            StringBuilder result = new StringBuilder(String.format("%s is destroyed.", object));

            final Area location = ge.getAreaOrDefault(ge.getPlayer().getCurrentLocation());
            for (final String item : targetEntity.getInventory())
            {
                location.addToInventory(item);
                result.append("\n").append(object).append(" drops ").append(item);
            }

            removeEntity(targetEntity, ge);

            return result.toString();
        }
        else
        {
            return "You cannot fight that.";
        }
    }

    private void
    removeEntity(final Entity targetEntity, final GameEntities ge)
    {
        final Area location = ge.getAreaOrDefault(ge.getPlayer().getCurrentLocation());

        switch (targetEntity.getType()) {
            case "Item":
                location.removeFromInventory(targetEntity.getName());
                break;
            case "Obstacle":
                location.removeObstacle(targetEntity.getName());
                break;
            case "NPC":
                location.removeNpc(targetEntity.getName());
                break;
            default:
                break;
        }

    }
}
