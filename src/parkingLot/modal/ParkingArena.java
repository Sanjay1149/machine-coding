package parkingLot.modal;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParkingArena {
    String parkingArenaId;
    int noOfFloors;
    Floor[] floors;

    public String getParkingArenaId() {
        return parkingArenaId;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public ParkingArena(String parkingArenaId, int noOfFloors, int noOfSlotsPerFloor) {
        this.parkingArenaId = parkingArenaId;
        this.noOfFloors = noOfFloors;
        createParkingFloors(noOfFloors, noOfSlotsPerFloor);
    }

    void createParkingFloors(int noOfFloors, int noOfSlotsPerFloor) {
        floors = Stream.iterate(0, x-> x+1 ).limit(noOfFloors).map(i -> new Floor(noOfSlotsPerFloor)).toArray(Floor[]::new);
    }
}
