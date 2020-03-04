import booster.init.ActionInitializer;
import booster.init.GameEntities;
import booster.init.EntityInitializer;
//import entity.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main (String[] args){

        //System.out.println("Welcome to Software Design");
        GameEntities gameEntities = EntityInitializer.loadGameFiles();

////////////////////////////////////////////////// Test actions //////////////////////////////////////////////////////////////////////
        ActionInitializer.PopulateAction(gameEntities);

        String action = gameEntities.getPlayer().takeActionOrDefault("move").apply("forest");
        System.out.println(action);

        action = gameEntities.getPlayer().takeActionOrDefault("search").apply("");
        System.out.println(action);

        action = gameEntities.getPlayer().takeActionOrDefault("use").apply("blood battle");
        System.out.println(action);

        action = gameEntities.getPlayer().takeActionOrDefault("interact").apply("");
        System.out.println(action);

        action = gameEntities.getPlayer().takeActionOrDefault("inventory").apply("");
        System.out.println(action);

        action = gameEntities.getPlayer().takeActionOrDefault("I want to fly").apply("Hell");
        System.out.println(action);
    }
}