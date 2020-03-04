//import entity.Command;


public class Main {
    public static void main (String[] args){

        //System.out.println("Welcome to Software Design");
        GameEntities gameEntities = Initializer.loadGameFiles();
        Engine engine = new Engine();

        engine.runGame();

////////////////////////////////////////////////// Test actions //////////////////////////////////////////////////////////////////////
        CommandInitializer.PopulateAction(gameEntities);

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