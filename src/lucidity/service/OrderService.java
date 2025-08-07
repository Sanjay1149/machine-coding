package lucidity.service;

import lucidity.model.Order;

import java.util.*;

public class OrderService {
    private final Map<Integer, Order> orderMap = new HashMap<>();
    private final Map<Integer, List<Order>> customerOrderMap = new HashMap<>();

    public void addOrder(Order order) {
        orderMap.put(order.getOrderId(), order);

        int customerId = order.getCustomer().getId();
        customerOrderMap.computeIfAbsent(customerId, k -> new ArrayList<>()).add(order);
    }

    public Order getOrderById(int orderId) {
        return orderMap.get(orderId);
    }

    public List<Order> getOrderByCustomerId(int customerId) {
        return customerOrderMap.get(customerId);
    }

    public boolean isOrderReady(int orderId) {
        Order order = orderMap.get(orderId);
        return order != null && order.isOrderReady();
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderMap.values());
    }

    public void removeOrder(int orderId) {
        Order order = orderMap.remove(orderId);
        if (order != null) {
            int customerId = order.getCustomer().getId();
            List<Order> customerOrders = customerOrderMap.get(customerId);
            if (customerOrders != null) {
                customerOrders.remove(order);
                if (customerOrders.isEmpty()) {
                    customerOrderMap.remove(customerId);
                }
            }
        }
    }
}