package lucidity.service;

import lucidity.model.*;
import lucidity.util.GeoUtils;

import java.util.*;

public class DeliverySimulationService {

    /**
     * backtrack method generates all the permutations with restaurants and customers
     * @param steps
     * @param path
     * @param results
     */
    private void backtrack(List<DeliveryStep> steps, List<DeliveryStep> path,
                           List<List<DeliveryStep>> results) {
        if (path.size() == steps.size()) {
            results.add(new ArrayList<>(path));
            return;
        }

        for (DeliveryStep step : steps) {
            if (path.contains(step)) continue;

            if (step instanceof Dropoff) {
                Order order = ((Dropoff) step).order;
                // We make sure the restaurant pickup is done, before adding the dropoff
                if (!path.contains(new Pickup(order))) continue;
            }

            path.add(step);
            backtrack(steps, path, results);
            path.removeLast();
        }
    }

    /**
     * generateValidPaths will generate the paths permutations
     * aman delivery agent will be the first, followed by restaurant and customers
     * @param agent
     * @param orders
     * @return
     */
    public List<List<Location>> generateValidPaths(DeliveryAgent agent, List<Order> orders) {
        List<DeliveryStep> steps = new ArrayList<>();
        for (Order order : orders) {
            steps.add(new Pickup(order));
            steps.add(new Dropoff(order));
        }

        List<List<DeliveryStep>> validStepPermutations = new ArrayList<>();
        backtrack(steps, new ArrayList<>(), validStepPermutations);

        List<List<Location>> finalPaths = new ArrayList<>();
        for (List<DeliveryStep> perm : validStepPermutations) {
            List<Location> path = new ArrayList<>();
            path.add(agent.getLocation()); // always start with Aman
            for (DeliveryStep step : perm) {
                path.add(step.getLocation());
            }
            finalPaths.add(path);
        }

        return finalPaths;
    }

    /**
     * findOptimalRoute finds the best optimal route that takes less time to make all deliveries
     *
     * @param agent
     * @param orders
     */
    public void findOptimalRoute(DeliveryAgent agent, List<Order> orders) {
        List<List<Location>> paths = generateValidPaths(agent, orders);

        double minTime = Double.MAX_VALUE;
        List<Location> bestPath = null;

        Map<Location, Integer> prepTimes = new HashMap<>();
        for (Order order : orders) {
            prepTimes.put(order.getRestaurant().getLocation(), order.getPrepTime());
        }

        for (List<Location> path : paths) {
            double time = simulateTime(path, prepTimes);
            if (time < minTime) {
                minTime = time;
                bestPath = path;
            }
        }

        System.out.println("Best path:");
        for (Location l : bestPath) {
            System.out.print(l.getName() + " -> ");
        }
        System.out.println("Done");
        System.out.println("Total delivery time: " + minTime + " minutes");
    }


    /**
     * simulateTime will go through all the permutation and calculate the total time it takes
     *
     * @param path
     * @param prepTimes
     * @return
     */
    private double simulateTime(List<Location> path, Map<Location, Integer> prepTimes) {
        double currentTime = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            Location from = path.get(i);
            Location to = path.get(i + 1);

            double travel = GeoUtils.travelTimeInMinutes(from, to);
            currentTime += travel;

            if (prepTimes.containsKey(to)) {
                int prepTime = prepTimes.get(to);
                double waitTime = Math.max(0, prepTime - currentTime);
                currentTime += waitTime;
            }
        }

        return currentTime;
    }
}