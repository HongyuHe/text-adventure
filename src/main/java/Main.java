//import entity.Command;


import Dictionary.GameEntities;

public class Main {
    public static void main (String[] args) {
//        GameEntities gameEntities = Initializer.loadGameFiles();
        Engine engine = new Engine();
        engine.startMenu();
    }
}