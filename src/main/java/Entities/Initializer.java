package Entities;

import CustomDeserialisers.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static Entities.EmptyEntity.initializeEmptyEntity;

public abstract class Initializer {

    public static GameEntities loadGameFiles() {
        Map<String, Item> itemEntities = loadMap("src/main/resources/data/items.json", new ItemDeserializer(), Item.class);
        Map<String, Area> areaEntities = loadMap("src/main/resources/data/areas.json", new AreaDeserializer(), Area.class);
        Map<String, Obstacle> obstacleEntities = loadMap("src/main/resources/data/obstacles.json", new ObstacleDeserializer(), Obstacle.class);
        Map<String, Npc> npcEntities = loadMap("src/main/resources/data/npcs.json", new NpcDeserializer(), Npc.class);
        GameOverItem gameOverItem = loadSingleEntity("src/main/resources/data/gameOverItem.json", new GameOverItemDeserializer(), GameOverItem.class);
        Player player = loadSingleEntity("src/main/resources/data/player.json", new PlayerDeserializer(), Player.class);
        EmptyEntity emptyEntity = initializeEmptyEntity();

        return new GameEntities(itemEntities,
                                areaEntities,
                                obstacleEntities,
                                npcEntities,
                                gameOverItem,
                                player,
                                emptyEntity
                );
    }

    private static <T> Map<String, T> loadMap(String jsonLocation, JsonDeserializer deserializer, Class<T> c) {
        try {
            Gson gson = new GsonBuilder()
                            .registerTypeAdapter(IEntity.class, deserializer)
                            .create();

            Reader reader = Files.newBufferedReader(Paths.get(jsonLocation));
            Type entitySetType = TypeToken.getParameterized(ArrayList.class, c).getType();
            ArrayList<T> entitySet = gson.fromJson(reader, entitySetType);
            reader.close();



            return entitySet.stream()
                    .collect(Collectors.toMap(e -> c.getName(), e -> e, (oldVal, newVal) -> newVal));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static <T> T loadSingleEntity(String jsonLocation, JsonDeserializer deserializer, Class<T> c) {
        try {
            Gson gson = new GsonBuilder()
                            .registerTypeAdapter(IEntity.class, deserializer)
                            .create();

            Reader reader = Files.newBufferedReader(Paths.get(jsonLocation));
            T singleEntity = gson.fromJson(reader, c);

            reader.close();
            return singleEntity;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
