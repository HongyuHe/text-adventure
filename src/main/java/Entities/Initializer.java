package Entities;

import java.util.Set;

public abstract class Initializer {

    public static GameEntities loadGameFiles() {
        Set<Item> itemEntities = Item.getEntities();


        return new GameEntities();
    }



}
