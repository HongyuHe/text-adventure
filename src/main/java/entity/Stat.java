package entity;

public class Stat {
    private String name;
    private Integer value;

    public Stat() { new Stat("", 0); }

    public Stat(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }

    public Integer getValue() { return value; }
}

// TODO: refactor this class out - stats can just be name:value pairs in a HashMap