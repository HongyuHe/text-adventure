import Entities.Area;
import Entities.Initializer;
import Entities.GameEntities;
import Entities.Item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main (String[] args){
//        System.out.println("Welcome to Software Design");
        GameEntities gameEntities = Initializer.loadGameFiles();
        Engine engine = new Engine();

        engine.runGame();

        //System.out.println(gameEntities.getAreaEntities().get("forest").connectsTo("north"));
    }
}