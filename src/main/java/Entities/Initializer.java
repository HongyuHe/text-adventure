package Entities;

import CustomDeserialisers.AreaDeserializer;
import CustomDeserialisers.ItemDeserializer;
import CustomDeserialisers.ObstacleDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public abstract class Initializer {

    public static GameEntities loadGameFiles() {
        Set<Item> itemEntities = load("src/main/resources/data/items.json", new ItemDeserializer(), Item.class);
        Set<Area> areaEntities = load("src/main/resources/data/areas.json", new AreaDeserializer(), Area.class);
        Set<Obstacle> obstacleEntities = load("src/main/resources/data/obstacles.json", new ObstacleDeserializer(), Obstacle.class);
        //System.out.println(areaEntities);
        for (Obstacle item : obstacleEntities) {
            item.commands.forEach(e -> System.out.println(e.params));
        }

        return new GameEntities();
    }

    private static <T> Set<T> load(String jsonLocation, JsonDeserializer deserializer, Class<T> c) {
        try {
            Gson gson = new GsonBuilder()
                            .registerTypeAdapter(IEntity.class, deserializer)
                            .create();

            Reader reader = Files.newBufferedReader(Paths.get(jsonLocation));
            Type entitySetType = TypeToken.getParameterized(Set.class, c).getType();
            Set<T> entitySet = gson.fromJson(reader, entitySetType);

            reader.close();
            return entitySet;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



}
