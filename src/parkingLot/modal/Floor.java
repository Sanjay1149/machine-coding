package parkingLot.modal;

import parkingLot.vehicles.SlotType;

import java.util.HashMap;
import java.util.Map;

public class Floor {
    int floorId;
    int noOfSlotsPerFloor;
    Map<Integer, VehicleSlot> slotPositionMap = new HashMap<>();
    Map<SlotType, SlotInfo> slotInfoMap = new HashMap<>();

    Floor(int noOfSlotsPerFloor) {

        trunkStartSlot = 1;
        noOfTruck = 1;
        vacantTrunkSlot = noOfTruck;
        bikeStartSlot = vacantTrunkSlot + 1;
        noOfBike = 2;
        vacantBikeSlot = noOfBike;
        carStartSlot = vacantTrunkSlot + vacantBikeSlot + 1;
        noOfCar = noOfSlotsPerFloor - vacantTrunkSlot - vacantBikeSlot;
        vacantCarSlot = noOfCar;

        vacantSlots = noOfSlotsPerFloor;
        createFloorSlots(noOfSlotsPerFloor);
    }

    void createFloorSlots(int noOfSlotsPerFloor) {
        slots = new VehicleSlot[noOfSlotsPerFloor];
        for (int slotCount = 1; slotCount <= noOfSlotsPerFloor; slotCount++) {
            if (slotCount <= vacantTrunkSlot) {
                slots[slotCount - 1] = new VehicleSlot("TRUCK");
            } else if (slotCount <= vacantTrunkSlot + vacantBikeSlot) {
                slots[slotCount - 1] = new VehicleSlot("BIKE");
            } else if (slotCount <= vacantTrunkSlot + vacantBikeSlot + vacantCarSlot) {
                slots[slotCount - 1] = new VehicleSlot("CAR");
            }
        }
    }

    public VehicleSlot[] getSlots() {
        return slots;
    }

    public int getNoOfSlotsPerFloor() {
        return noOfSlotsPerFloor;
    }

//    int numOfVacantSlots() {
//        return vacantSlots;
//    }

    public int getVacantTrunkSlot() {
        return vacantTrunkSlot;
    }

    public int getVacantBikeSlot() {
        return vacantBikeSlot;
    }

    public int getVacantCarSlot() {
        return vacantCarSlot;
    }

    public int getVacantSlotBasedOnType(String vehicleType) {
        if (vehicleType.equals("TRUCK")) {
            return getVacantTrunkSlot();
        } else if (vehicleType.equals("BIKE")) {
            return getVacantBikeSlot();
        } else if (vehicleType.equals("CAR")) {
            return getVacantCarSlot();
        }
        return 0;
    }

    public void vehicleAddedToFloor(String vehicleType) {
        if (vehicleType.equals("TRUCK")) {
            this.vacantTrunkSlot-=1;
        } else if (vehicleType.equals("BIKE")) {
            this.vacantBikeSlot-=1;
        } else if (vehicleType.equals("CAR")) {
            this.vacantCarSlot-=1;
        }
    }

    public void vehicleLeftFromFloor(String vehicleType) {
        if (vehicleType.equals("TRUCK")) {
            this.vacantTrunkSlot+=1;
        } else if (vehicleType.equals("BIKE")) {
            this.vacantBikeSlot+=1;
        } else if (vehicleType.equals("CAR")) {
            this.vacantCarSlot+=1;
        }
    }

    public int getTrunkStartSlot() {
        return trunkStartSlot;
    }

    public int getBikeStartSlot() {
        return bikeStartSlot;
    }

    public int getCarStartSlot() {
        return carStartSlot;
    }

    public int getVehicleStartPositionBasedOnType(String vehicleType) {
        if (vehicleType.equals("TRUCK")) {
            return getTrunkStartSlot();
        } else if (vehicleType.equals("BIKE")) {
            return getBikeStartSlot();
        } else if (vehicleType.equals("CAR")) {
            return getCarStartSlot();
        }
        return 0;
    }

    public int getNoOfTruck() {
        return noOfTruck;
    }

    public int getNoOfBike() {
        return noOfBike;
    }

    public int getNoOfCar() {
        return noOfCar;
    }

    public int getVehicleCountBasedOnType(String vehicleType) {
        if (vehicleType.equals("TRUCK")) {
            return getNoOfTruck();
        } else if (vehicleType.equals("BIKE")) {
            return getNoOfBike();
        } else if (vehicleType.equals("CAR")) {
            return getNoOfCar();
        }
        return 0;
    }
}
