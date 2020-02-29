package Entities;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import CustomDeserialisers.ItemDeserializer;

public class Item implements IEntity {
    private String type;
    private String name;
    private String description;
    private Boolean consumable;
    private Boolean active;
    private Stat stat;

    public Item(String type,
         String name,
         String description,
         Boolean consumable,
         Boolean active,
         Stat stat) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.consumable = consumable;
        this.active = active;
        this.stat = stat;
    }

    public static class Stat {
        private String name;
        private Integer value;

        public Stat(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isConsumable() {
        return consumable;
    }

    public Boolean isActive() {
        return active;
    }
/*
    public String getStatName() {
        return stat.name;
    }

    public Integer getStatValue() {
        return stat.value;
    }
*/
}
