package parkingLot;

import parkingLot.modal.ParkingArena;
import parkingLot.service.ParkingService;

import java.util.Scanner;

public class ParkingLot {
    static ParkingArena parkingArena;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        ParkingService parkingService = new ParkingService();

        String inputQuery;
        while (true) {
            inputQuery = in.nextLine();
            String[] subQueries = inputQuery.split(" ");
            if (subQueries[0].equals("create_parking_lot")) {
                String parkingArenaId = subQueries[1];
                int noOfFloors = Integer.parseInt(subQueries[2]);
                int noOfSlotsPerFloor = Integer.parseInt(subQueries[3]);
                parkingArena = new ParkingArena(parkingArenaId, noOfFloors, noOfSlotsPerFloor);
            } else if (subQueries[0].equals("display")) {
                if (subQueries[1].equals("free_count")) {
                    parkingService.displayFreeCount(parkingArena, subQueries[2]);
                } else if (subQueries[1].equals("free_slots")) {
                    parkingService.displayFreeSlots(parkingArena, subQueries[2]);
                } else {
                    parkingService.displayOccupiedSlots(parkingArena, subQueries[2]);
                }
            } else if (subQueries[0].equals("park_vehicle")) {
                parkingService.parkVehicle(parkingArena, subQueries[1], subQueries[2], subQueries[3]);
            } else if (subQueries[0].equals("unpark_vehicle")) {
                parkingService.unParkVehicle(parkingArena, subQueries[1]);
            } else if (subQueries[0].equals("exit")) {
                break;
            }
        }
    }
}
