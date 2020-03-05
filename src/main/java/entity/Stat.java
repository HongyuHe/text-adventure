package entity;

public class Stat {
    private String name;
    private Integer value;

    public Stat(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }

    public Integer getValue() { return value; }
}

