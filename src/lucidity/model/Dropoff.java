package lucidity.model;

public class Dropoff extends DeliveryStep {
    public Order order;

    public Dropoff(Order order) {
        this.order = order;
    }

    @Override
    public Location getLocation() {
        return order.getCustomer().getLocation();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Dropoff && ((Dropoff) o).order.equals(this.order);
    }

    @Override
    public int hashCode() {
        return order.hashCode();
    }
}
