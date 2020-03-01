package Entities;

import java.util.Set;

public class GameEntities {
    private Set<Item> itemEntities;
    private Set<Area> areaEntities;
    private Set<Obstacle> obstacleEntities;
    private Set<Npc> npcEntities;
    private GameOverItem gameOverItem;
    private Player player;
    private EmptyEntity emptyEntity;

    GameEntities(Set<Item >itemEntities,
                        Set<Area> areaEntities,
                        Set<Obstacle> obstacleEntities,
                        Set<Npc> npcEntities,
                        GameOverItem gameOverItem,
                        Player player,
                        EmptyEntity emptyEntity) {

        this.areaEntities = areaEntities;
        this.obstacleEntities = obstacleEntities;
        this.npcEntities = npcEntities;
        this.itemEntities = itemEntities;
        this.gameOverItem = gameOverItem;
        this.player = player;
        this.emptyEntity = emptyEntity;
    }

    public Set<Item> getItemEntities() { return itemEntities; }

    public Set<Area> getAreaEntities() { return areaEntities; }

    public Set<Obstacle> getObstacleEntities() { return obstacleEntities; }

    public Set<Npc> getNpcEntities() { return npcEntities; }

    public GameOverItem getGameOverItem() { return gameOverItem; }

    public Player getPlayer() { return player; }

    public EmptyEntity getEmptyEntity() { return emptyEntity; }

}
