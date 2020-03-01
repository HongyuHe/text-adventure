package Entities;

public class EmptyEntity implements IEntity {
    private static final EmptyEntity EmptyEntityInstance = new EmptyEntity();
    private String name;
    private String description;

    //private constructor to avoid client applications to use constructor
    private EmptyEntity(){
        name = "";
        description = "";
    }

    public static EmptyEntity initializeEmptyEntity(){
        return EmptyEntityInstance;
    }
}
