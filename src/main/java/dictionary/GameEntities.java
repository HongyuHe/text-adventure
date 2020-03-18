package dictionary;

import entity.*;

import java.util.HashMap;
import java.util.Map;

public class GameEntities {
    private final Map<String, Item> itemEntities;
    private final Map<String, Area> areaEntities;
    private final Map<String, Obstacle> obstacleEntities;
    private final Map<String, Npc> npcEntities;
    private final Item gameOverItem;
    private final Player player;
    private final DefaultEntity defaultEntity;

    public GameEntities(Map<String, Item> itemEntities,
                        Map<String, Area> areaEntities,
                        Map<String, Obstacle> obstacleEntities,
                        Map<String, Npc> npcEntities,
                        Item gameOverItem,
                        Player player,
                        DefaultEntity defaultEntity) {

        this.itemEntities = itemEntities;
        this.areaEntities = areaEntities;
        this.obstacleEntities = obstacleEntities;
        this.npcEntities = npcEntities;
        this.gameOverItem = gameOverItem;
        this.player = player;
        this.defaultEntity = defaultEntity;

        itemEntities.put(gameOverItem.getName(), gameOverItem);
    }

    public Entity getEntityOrDefault(String entity) {
        if (itemEntities.containsKey(entity)) {
            return itemEntities.get(entity);
        } else if (areaEntities.containsKey(entity)) {
            return areaEntities.get(entity);
        } else if (obstacleEntities.containsKey(entity)) {
            return obstacleEntities.get(entity);
        } else if (npcEntities.containsKey(entity)) {
            return npcEntities.get(entity);
        } else if (gameOverItem.getName().equals(entity)) {
            return gameOverItem;
        } else if (player.getName().equals(entity)) {
            return player;
        } else {
            return defaultEntity;
        }
    }

    public Map<String, Item> getItemEntities() { return new HashMap<>(itemEntities); }

    public Map<String, Area> getAreaEntities() { return new HashMap<>(areaEntities); }

    public Area getAreaOrDefault(final String area) { return areaEntities.getOrDefault(area, new Area()); }

    public Item getItemOrDefault(final String item) { return itemEntities.getOrDefault(item, new Item()); }

    public Obstacle getObstacleOrDefault(final String obstacle) { return obstacleEntities.getOrDefault(obstacle, new Obstacle()); }

    public Map<String, Obstacle> getObstacleEntities() { return new HashMap<>(obstacleEntities); }

    public Map<String, Npc> getNpcEntities() { return new HashMap<>(npcEntities); }

    public Item getGameOverItem() { return gameOverItem; }

    public Player getPlayer() { return player; }

    public DefaultEntity getDefaultEntity() { return defaultEntity; }

}
