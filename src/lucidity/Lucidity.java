package lucidity;

import lucidity.model.*;
import lucidity.service.DeliverySimulationService;
import lucidity.service.OrderService;


public class Lucidity {

    public static void main(String[] args) {
        Location amanLoc = new Location("Aman", 12.935, 77.614);
        Location r1Loc = new Location("R1", 12.936, 77.610);
        Location r2Loc = new Location("R2", 12.938, 77.612);
        Location c1Loc = new Location("C1", 12.937, 77.615);
        Location c2Loc = new Location("C2", 12.950, 77.617);
        Location r3Loc = new Location("R3", 12.938, 77.618);
        Location c3Loc = new Location("C3", 12.947, 77.620);

        DeliveryAgent aman = new DeliveryAgent(1, "Aman", amanLoc);

        Customer c1 = new Customer(1, "C1", c1Loc);
        Customer c2 = new Customer(2, "C2", c2Loc);
        Customer c3 = new Customer(3, "C3", c3Loc);

        Restaurant r1 = new Restaurant("R1", r1Loc);
        Restaurant r2 = new Restaurant("R2", r2Loc);
        Restaurant r3 = new Restaurant("R3", r3Loc);

        Order o1 = new Order(101, 4, aman, c1, r1); // R1 -> C1
        Order o2 = new Order(102, 7, aman, c2, r2); // R2 -> C2
        Order o3 = new Order(103, 1, aman, c3, r3); // R3 -> C3
        OrderService orderService = new OrderService();
        orderService.addOrder(o1);
        orderService.addOrder(o2);
        orderService.addOrder(o3);

        DeliverySimulationService simulator = new DeliverySimulationService();
        simulator.findOptimalRoute(aman, orderService.getAllOrders());

    }
}
