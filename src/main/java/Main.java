import Entities.Initializer;
import Entities.GameEntities;

public class Main {
    public static void main (String[] args){
        //System.out.println("Welcome to Software Design");
        GameEntities gameEntities = Initializer.loadGameFiles();
        System.out.println(gameEntities.player.isActive());

    }
}