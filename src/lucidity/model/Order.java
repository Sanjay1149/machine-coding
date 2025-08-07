package lucidity.model;

public class Order {
    private final int orderId;
    private final int prepTime;
    private final long startTime;
    DeliveryAgent agent;
    Customer customer;
    Restaurant restaurant;

    public Order(int orderId, int prepTime, DeliveryAgent agent, Customer customer, Restaurant restaurant) {
        this.orderId = orderId;
        this.prepTime = prepTime;
        this.agent = agent;
        this.customer = customer;
        this.restaurant = restaurant;
        startTime = System.currentTimeMillis();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public DeliveryAgent getAgent() {
        return agent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public boolean isOrderReady() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        return elapsedTime >= prepTime * 60_000L;
    }

}