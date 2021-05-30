package parkingLot.modal;

import parkingLot.vehicles.IVehicle;

public class VehicleSlot {
    int slotId;
    IVehicle vehicle;
    String slotType;
    boolean isOccupied;

    VehicleSlot(String slotType) {
        this.slotType = slotType;
        this.isOccupied = false;
    }

    public void addVehicle(IVehicle vehicle) {
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

    public IVehicle getVehicle() {
        return vehicle;
    }

}
