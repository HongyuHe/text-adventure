package entity;

public class EmptyEntity extends Entity {
    private static final EmptyEntity EmptyEntityInstance = new EmptyEntity();

    private EmptyEntity(){}

    public static EmptyEntity initializeEmptyEntity(){
        return EmptyEntityInstance;
    }

    @Override
    public String getType() { return "EmptyEntity"; }

    @Override
    public String getName() {
        return "EmptyEntity";
    }

    @Override
    public String getDescription() {
        return "You cannot find that here.";
    }

    @Override
    public Boolean isActive() {
        return false;
    }
}
