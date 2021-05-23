package parkingLot.modal;

public class Vehicle {
    String type;
    String registrationNum;
    String color;

    public Vehicle(){}
    
    public Vehicle(String vehicleType, String registrationNum, String color) {
        this.type = vehicleType;
        this.registrationNum = registrationNum;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public String getColor() {
        return color;
    }

}
/*

class Bike extends Vehicle {

    Bike() {
    }

    Bike(String registrationNum, String color) {
        this.type = "BIKE";
        this.registrationNum = registrationNum;
        this.color = color;
    }

}

class Car extends Vehicle {
    String type;
    String registrationNum;
    String color;

    Car() {

    }

    Car(String registrationNum, String color) {
        this.type = "CAR";
        this.registrationNum = registrationNum;
        this.color = color;
    }

}

class Truck extends Vehicle {

    Truck() {
    }

    Truck(String registrationNum, String color) {
        this.type = "TRUCK";
        this.registrationNum = registrationNum;
        this.color = color;
    }

}*/
