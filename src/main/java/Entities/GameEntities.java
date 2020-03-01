package Entities;

import java.util.Set;

public class GameEntities {
    Set<Item> itemEntities;
    Set<Area> areaEntities;
    Set<Obstacle> obstacleEntities;
    Set<Npc> npcEntities;
    GameOverItem gameOverItem;
    public Player player;
    EmptyEntity emptyEntity;

    public GameEntities(Set<Item >itemEntities,
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

}
