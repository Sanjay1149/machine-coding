package lucidity.model;

public class Pickup extends DeliveryStep {
    Order order;

    public Pickup(Order order) {
        this.order = order;
    }

    @Override
    public Location getLocation() {
        return order.getRestaurant().getLocation();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Pickup && ((Pickup) o).order.equals(this.order);
    }

    @Override
    public int hashCode() {
        return order.hashCode();
    }
}
