package entity;

public abstract class Entity { // NOSONAR - Entity is intended to be used as an API, an interface is not appropriate here

    public abstract String getType();

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean isActive();

    public abstract void setActive(final boolean value);
}
