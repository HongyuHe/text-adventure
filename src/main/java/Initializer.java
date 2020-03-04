import Command.CommandFactory;
import Command.ICommand;
import deserialiser.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import Entities.*;
import Entities.EmptyEntity;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static Entities.EmptyEntity.initializeEmptyEntity;

public abstract class Initializer {

    public static GameEntities loadGameFiles() {
        Map<String, Item> itemEntities = load("src/main/resources/data/items.json", new ItemDeserializer(), Item.class)
                .stream().collect(Collectors.toMap(Item::getName, Function.identity()));
        Map<String, Area> areaEntities = load("src/main/resources/data/areas.json", new AreaDeserializer(), Area.class)
                .stream().collect(Collectors.toMap(Area::getName, Function.identity()));
        Map<String, Obstacle> obstacleEntities = load("src/main/resources/data/obstacles.json", new ObstacleDeserializer(), Obstacle.class)
                .stream().collect(Collectors.toMap(Obstacle::getName, Function.identity()));
        Map<String, Npc> npcEntities = load("src/main/resources/data/npcs.json", new NpcDeserializer(), Npc.class)
                .stream().collect(Collectors.toMap(Npc::getName, Function.identity()));

        GameOverItem gameOverItem = loadSingleEntity("src/main/resources/data/gameOverItem.json", new GameOverItemDeserializer(), GameOverItem.class);
        Player player = loadSingleEntity("src/main/resources/data/player.json", new PlayerDeserializer(), Player.class);
        EmptyEntity emptyEntity = initializeEmptyEntity();

        GameEntities gInit = new GameEntities(itemEntities,
                                areaEntities,
                                obstacleEntities,
                                npcEntities,
                                gameOverItem,
                                player,
                                emptyEntity
                );
        populateActions(gInit);
        return gInit;
    }

    private static <T> ArrayList<T> load(String jsonLocation, JsonDeserializer deserializer, Class<T> c) {
        try {
            Gson gson = new GsonBuilder()
                            .registerTypeAdapter(IEntity.class, deserializer)
                            .create();

            Reader reader = Files.newBufferedReader(Paths.get(jsonLocation));
            Type entitySetType = TypeToken.getParameterized(ArrayList.class, c).getType();
            ArrayList<T> entitySet = gson.fromJson(reader, entitySetType);
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

    private static void populateActions(GameEntities entities) {
        HashMap<String, ICommand> actions = new HashMap<>();
        Player player = entities.getPlayer();

        for (CommandBlueprint cmd : player.getCommands()) {
            actions.putIfAbsent(cmd.getName(), CommandFactory.createCommand(cmd));
        }
        player.setActions(new HashMap<>(actions));
    }
}
