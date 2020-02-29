package Initializer;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class ItemEntity extends Initializer {
    String type;
    String name;
    String description;
    Boolean consumable;
    Boolean active;
    Map<String, Integer> stat;

    public ItemEntity() {}

    public ItemEntity(String type,
                      String name,
                      String description,
                      Boolean consumable,
                      Boolean active,
                      Map<String, Integer> stat) {

        this.type = type;
        this.name = name;
        this.description = description;
        this.consumable = consumable;
        this.active = active;
        this.stat = stat;
    }

    static Set<ItemEntity> getEntities() {
        try {

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/data/items.json"));

            // convert JSON file to map
            List<ItemEntity> entities = new Gson().fromJson(reader, new TypeToken<List<ItemEntity>>() {}.getType());

            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return null;
    }

}
