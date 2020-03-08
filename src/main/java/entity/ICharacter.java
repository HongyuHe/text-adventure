package entity;

import java.util.Map;
import java.util.Set;

public interface ICharacter {

    Set<String> getInventory();

    void addToInventory(final String object);

    void removeFromInventory(final String object);

    boolean hasInInventory(final String object);

    Map<String, Integer> getStats();

    void setStat(final String name, final Integer value);

    Integer getStatValue(final String name);
}
