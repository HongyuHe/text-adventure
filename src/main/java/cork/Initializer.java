package cork;

import command.CommandFactory;
import command.Command;
import deserialiser.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import entity.*;
import dictionary.GameEntities;
import org.tinylog.Logger;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static entity.EmptyEntity.initializeEmptyEntity;

public class Initializer {
    private Initializer() {}

    public static
    GameEntities loadGameFiles(String game)
    {
        final String jsonLocation = "./games/" + game;
        final String itemLocation = jsonLocation + "/items.json";
        final String areaLocation = jsonLocation + "/areas.json";
        final String obstacleLocation = jsonLocation + "/obstacles.json";
        final String npcLocation = jsonLocation + "/npcs.json";
        final String gameOverItemLocation = jsonLocation + "/gameOverItem.json";
        final String playerLocation = jsonLocation + "/player.json";

        Map<String, Item> itemEntities =
                load(itemLocation, new ItemDeserializer(), Item.class)
                        .stream()
                        .collect(Collectors.toMap(
                                Item::getName,
                                Function.identity()));

        Map<String, Area> areaEntities =
                load(areaLocation, new AreaDeserializer(), Area.class)
                        .stream()
                        .collect(Collectors.toMap(
                                Area::getName,
                                Function.identity()));

        Map<String, Obstacle> obstacleEntities =
                load(obstacleLocation, new ObstacleDeserializer(), Obstacle.class)
                        .stream()
                        .collect(Collectors.toMap(
                                Obstacle::getName,
                                Function.identity()));

        Map<String, Npc> npcEntities =
                load(npcLocation, new NpcDeserializer(), Npc.class)
                        .stream()
                        .collect(Collectors.toMap(
                                Npc::getName,
                                Function.identity()));

        Item gameOverItem = loadSingleEntity(gameOverItemLocation, new ItemDeserializer(), Item.class);

        Player player = loadSingleEntity(playerLocation, new PlayerDeserializer(), Player.class);

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

    private static <T> ArrayList<T>
    load(String jsonLocation, JsonDeserializer<?> deserializer, Class<T> c)
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(jsonLocation))) {
            Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Entity.class, deserializer)
                            .create();

            Type entitySetType = TypeToken.getParameterized(ArrayList.class, c).getType();
            return gson.fromJson(reader, entitySetType);

        } catch (Exception ex) {
            Logger.error("JSON file: " + jsonLocation + "\n" + ex.toString());
        }

        return new ArrayList<>();
    }

    private static <T> T
    loadSingleEntity(String jsonLocation, JsonDeserializer<?> deserializer, Class<T> c)
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(jsonLocation))) {
            Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Entity.class, deserializer)
                            .create();

            return gson.fromJson(reader, c);

        } catch (Exception ex) {
            Logger.error("JSON file: " + jsonLocation + "\n" + ex.toString());
        }

        return null;
    }

    private static void
    populateActions(GameEntities entities)
    {
        HashMap<String, Command> actions = new HashMap<>();
        Player player = entities.getPlayer();

        for (CommandBlueprint cmd : player.getCommands()) {
            actions.putIfAbsent(cmd.getName(), CommandFactory.createCommand(cmd, player));
        }
        player.setActions(new HashMap<>(actions));

        for (Obstacle o : entities.getObstacleEntities().values())
        {
            actions = new HashMap<>();

            for (CommandBlueprint cmd : o.getCommands()) {
                actions.putIfAbsent(cmd.getName(), CommandFactory.createCommand(cmd, o));
            }

            o.setActions(new HashMap<>(actions));
        }
    }
}
