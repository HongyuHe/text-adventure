package cork;

import command.CommandFactory;
import command.ICommand;
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

        DefaultEntity defaultEntity = DefaultEntity.instance();

        GameEntities gInit = new GameEntities(itemEntities,
                                areaEntities,
                                obstacleEntities,
                                npcEntities,
                                gameOverItem,
                                player,
                defaultEntity
                );
        populateCommands(gInit);
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
    populateCommands(GameEntities entities)
    {
        iterateAndPopulate(entities.getItemEntities());
        iterateAndPopulate(entities.getAreaEntities());
        iterateAndPopulate(entities.getObstacleEntities());
        iterateAndPopulate(entities.getNpcEntities());

        Item goi = entities.getGameOverItem();
        goi.setActions(createCommandMap(goi));

        Player p = entities.getPlayer();
        p.setActions(createCommandMap(p));

        DefaultEntity ee = entities.getDefaultEntity();
        ee.setActions(createCommandMap(ee));
    }

    private static <T extends Entity> void
    iterateAndPopulate(final Map<String, T> c)
    {
        c.values().forEach(e -> e.setActions(createCommandMap(e)));
    }

    private static Map<String, ICommand>
    createCommandMap(final Entity e)
    {
        return e.getCommandBlueprints()
                .stream()
                .collect(Collectors.toMap(
                        CommandBlueprint::getName,
                        cb -> CommandFactory.createCommand(cb, e),
                        (a, b) -> b,
                        HashMap::new)
                );
    }
}
