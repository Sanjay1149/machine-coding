package lucidity.model;

import java.util.Objects;

public class DeliveryStep {
    private final DeliveryStepType type;
    private final Order order;

    public DeliveryStep(DeliveryStepType type, Order order) {
        this.type = type;
        this.order = order;
    }

    public DeliveryStepType getType() {
        return type;
    }

    public Order getOrder() {
        return order;
    }

    public Location getLocation() {
        if (type == DeliveryStepType.PICKUP) {
            return order.getRestaurant().getLocation();
        } else {
            return order.getCustomer().getLocation();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DeliveryStep)) return false;
        DeliveryStep other = (DeliveryStep) o;
        return this.type == other.type && this.order.getOrderId() == other.order.getOrderId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, order.getOrderId());
    }

    @Override
    public String toString() {
        return type + " for Order " + order.getOrderId() + " at " + getLocation().getName();
    }
}