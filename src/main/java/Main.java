//import entity.Command;


public class Main {
    public static void main (String[] args){

        //System.out.println("Welcome to Software Design");
        GameEntities gameEntities = Initializer.loadGameFiles();
//        Engine engine = new Engine();
//
//        engine.runGame();

////////////////////////////////////////////////// Test actions //////////////////////////////////////////////////////////////////////

        String action = gameEntities.getPlayer().findCommandOrElse("move").apply("forest");
        System.out.println(action);

        action = gameEntities.getPlayer().findCommandOrElse("search").apply("");
        System.out.println(action);

        action = gameEntities.getPlayer().findCommandOrElse("use").apply("blood battle");
        System.out.println(action);

        action = gameEntities.getPlayer().findCommandOrElse("interact").apply("");
        System.out.println(action);

        action = gameEntities.getPlayer().findCommandOrElse("inventory").apply("");
        System.out.println(action);

        action = gameEntities.getPlayer().findCommandOrElse("I want to fly").apply("Hell");
        System.out.println(action);

    }
}