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
import java.util.Set;

import static Entities.EmptyEntity.initializeEmptyEntity;

public abstract class Initializer {

    public static GameEntities loadGameFiles() {
        Set<Item> itemEntities = loadSet("src/main/resources/data/items.json", new ItemDeserializer(), Item.class);
        Set<Area> areaEntities = loadSet("src/main/resources/data/areas.json", new AreaDeserializer(), Area.class);
        Set<Obstacle> obstacleEntities = loadSet("src/main/resources/data/obstacles.json", new ObstacleDeserializer(), Obstacle.class);
        Set<Npc> npcEntities = loadSet("src/main/resources/data/npcs.json", new NpcDeserializer(), Npc.class);
        GameOverItem gameOverItem = loadSingleEntity("src/main/resources/data/gameOverItem.json", new GameOverItemDeserializer(), GameOverItem.class);
        Player player = loadSingleEntity("src/main/resources/data/player.json", new PlayerDeserializer(), Player.class);
        EmptyEntity emptyEntity = initializeEmptyEntity();

        return new GameEntities(itemEntities,
                                areaEntities,
                                obstacleEntities,
                                npcEntities,
                                gameOverItem,
                                player,
                                emptyEntity);
    }

    private static <T> Set<T> loadSet(String jsonLocation, JsonDeserializer deserializer, Class<T> c) {
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
