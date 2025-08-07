package lucidity.model;

public class DeliveryAgent {
    private final int id;
    private final String name;
    private Location currentLocation;

    public DeliveryAgent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void updateLocation(Location newLocation) {
        this.currentLocation = newLocation;
    }

    public Location getLocation() {
        return currentLocation;
    }
}