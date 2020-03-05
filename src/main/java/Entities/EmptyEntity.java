package Entities;

public class EmptyEntity extends Entity {
    private static final EmptyEntity EmptyEntityInstance = new EmptyEntity();

    private EmptyEntity(){}

    public static EmptyEntity initializeEmptyEntity(){
        return EmptyEntityInstance;
    }

    @Override
    public String getType() { return null; }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Boolean isActive() {
        return null;
    }
}
