import Initializer.Initializer;
import Initializer.GameEntities;

import static Initializer.Initializer.loadGameFiles;

public class Main {
    public static void main (String[] args){
        //System.out.println("Welcome to Software Design");
         GameEntities gameEntities = Initializer.loadGameFiles();
    }
}