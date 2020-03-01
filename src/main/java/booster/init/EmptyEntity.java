package booster.init;

public class EmptyEntity implements IEntity {
    private static final EmptyEntity EmptyEntityInstance = new EmptyEntity();

    private EmptyEntity(){}

    static EmptyEntity initializeEmptyEntity(){
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
