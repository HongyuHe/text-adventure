//import entity.Command;


import cork.Engine;

public class Main {
    public static void main (String[] args) {
//        GameEntities gameEntities = cork.Initializer.loadGameFiles();
        Engine engine = new Engine();
        engine.startMenu();
    }
}