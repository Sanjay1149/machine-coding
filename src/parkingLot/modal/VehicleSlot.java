package parkingLot.modal;

public class VehicleSlot {
    Vehicle vehicle;
    String slotType;
    boolean isOccupied;

    VehicleSlot(String slotType) {
        this.slotType = slotType;
        this.isOccupied = false;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public boolean hasVehicleOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}
