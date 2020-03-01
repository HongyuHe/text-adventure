import Entities.Area;
import Entities.Initializer;
import Entities.GameEntities;
import Entities.Item;

public class Main {
    public static void main (String[] args){
        //System.out.println("Welcome to Software Design");
        GameEntities gameEntities = Initializer.loadGameFiles();
        //System.out.println(gameEntities.getAreaEntities().get("forest").connectsTo("north"));
    }
}