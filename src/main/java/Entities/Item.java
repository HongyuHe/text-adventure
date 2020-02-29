package Entities;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class Item implements IEntity {
    private String type;
    private String name;
    private String description;
    private Boolean consumable;
    private Boolean active;
    private Stat stat;

    Item(String type,
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

    class Stat {
        private String name;
        private Integer value;

        Stat(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
    }

    static Set<Item> getEntities() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/data/items.json"));
            Set<Item> entities = new Gson().fromJson(reader, new TypeToken<Set<Item>>() {}.getType());
            reader.close();

            return entities;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        return null;
    }



}
