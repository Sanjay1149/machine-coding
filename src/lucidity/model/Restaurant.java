package lucidity.model;

public class Restaurant {
    private final String name;
    private final Location location;

    public Restaurant(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getRestaurantName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}