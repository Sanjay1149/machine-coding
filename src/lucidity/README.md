# Lucidity Module - Design Overview

## Overview
This module demonstrates a object-oriented design for a food delivery system.
It models core entities such as Restaurant, Customer, and DeliveryAgent using Java best practices.

## Structure
- **model package:** 
  - `Restaurant`: Defines the restaurant entities.
  - `User` (interface): Represents a generic user in the system.
  - `Customer` (class): representing the end-user who receives deliveries.
  - `DeliveryAgent` (class): representing the person delivering orders.
  - `DeliveryStep` (interface): Represents the delivery action to be done
  - `DeliveryStepType` (enum): representing the pickup from restaurant or drop off to customer

- **util package:**
  - `GeoUtils`: To calculate the distance traveled between two locations using haversine principle
- **service package:**
  - `OrderService`: To maintain and work with the order related operations
  - `DeliverySimulationService`: To calculate the optimal solution for Aman to create the restaurants and deliver it to the customers


## Example Usage
To Create the delivery agent
```java
Location amanLoc = new Location("Aman", 12.935, 77.614);
DeliveryAgent aman = new DeliveryAgent(1, "Aman", amanLoc);
```
To Create the customer
```java
Location c1Loc = new Location("C1", 12.937, 77.615);
Customer c1 = new Customer(1, "C1", c1Loc);
```

To Create the restaurant
```java
Location r1Loc = new Location("R1", 12.936, 77.610);
Restaurant r1 = new Restaurant("R1", r1Loc);
```

---
This module provides a solid foundation for building scalable and maintainable food delivery.