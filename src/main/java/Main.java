//import entity.Command;


import Dictionary.GameEntities;

public class Main {
    public static void main (String[] args) {

        //System.out.println("Welcome to Software Design");
        GameEntities gameEntities = Initializer.loadGameFiles();

////////////////////////////////////////////////// Test actions //////////////////////////////////////////////////////////////////////

        //Engine engine = new Engine();

        //engine.runGame();

//        IEntity entity = gameEntities.findEntityOrElse("forest");

//        System.out.println(entity.getName());
        String action = gameEntities.getPlayer().findCommandOrElse("move").apply("forest", gameEntities);
        System.out.println(action);
//
//        action = gameEntities.getPlayer().findCommandOrElse("search").apply("");
//        System.out.println(action);
//
//        action = gameEntities.getPlayer().findCommandOrElse("use").apply("blood battle");
//        System.out.println(action);
//
//        action = gameEntities.getPlayer().findCommandOrElse("interact").apply("");
//        System.out.println(action);
//
//        action = gameEntities.getPlayer().findCommandOrElse("inventory").apply("");
//        System.out.println(action);
//
//        action = gameEntities.getPlayer().findCommandOrElse("I want to fly").apply("Hell");
//        System.out.println(action);

    }
}