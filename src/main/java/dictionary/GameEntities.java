package dictionary;

import entity.EmptyEntity;
import entity.*;

import java.util.HashMap;
import java.util.Map;

public class GameEntities {
    private Map<String, Item> itemEntities;
    private Map<String, Area> areaEntities;
    private Map<String, Obstacle> obstacleEntities;
    private Map<String, Npc> npcEntities;
    private GameOverItem gameOverItem;
    private Player player;
    private EmptyEntity emptyEntity;

    public GameEntities(Map<String, Item> itemEntities,
                        Map<String, Area> areaEntities,
                        Map<String, Obstacle> obstacleEntities,
                        Map<String, Npc> npcEntities,
                        GameOverItem gameOverItem,
                        Player player,
                        EmptyEntity emptyEntity) {

        this.itemEntities = itemEntities;
        this.areaEntities = areaEntities;
        this.obstacleEntities = obstacleEntities;
        this.npcEntities = npcEntities;
        this.gameOverItem = gameOverItem;
        this.player = player;
        this.emptyEntity = emptyEntity;
    }

    public Entity findEntityOrElse(String entity) {
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
            return emptyEntity;
        }
    }

    public Map<String, Item> getItemEntities() { return new HashMap<>(itemEntities); }

    public Map<String, Area> getAreaEntities() { return new HashMap<>(areaEntities); }

    public Area getAreaOrElse(final String area) { return areaEntities.getOrDefault(area, new Area()); }

    public Item getItemOrElse(final String item) { return itemEntities.getOrDefault(item, new Item()); }

    public Map<String, Obstacle> getObstacleEntities() { return new HashMap<>(obstacleEntities); }

    public Map<String, Npc> getNpcEntities() { return new HashMap<>(npcEntities); }

    public GameOverItem getGameOverItem() { return gameOverItem; }

    public Player getPlayer() { return player; }

    public EmptyEntity getEmptyEntity() { return emptyEntity; }

}
