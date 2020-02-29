package Initializer;

import java.util.Set;

public abstract class Initializer {

    public static GameEntities loadGameFiles() {
        Set<ItemEntity> itemEntities = ItemEntity.getEntities();

        return new GameEntities();
    }



}
