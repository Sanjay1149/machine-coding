package lucidity.model;

public class Customer {
    private final int id;
    private final String name;
    private final Location location;

    public Customer(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

}