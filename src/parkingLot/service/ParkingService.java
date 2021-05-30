package parkingLot.service;

import parkingLot.modal.*;
import parkingLot.vehicles.*;

import java.util.HashMap;
import java.util.Map;

public class ParkingService {
    Map<String, Ticket> ticketMap = new HashMap<>();

    IVehicle getVehicleBasedOnType(String vehicleType, String registrationNum, String color) {
        if ( vehicleType.equals("CAR")) {
            return  new Car(SlotType.CAR, registrationNum, color);
        } else if ( vehicleType.equals("BIKE")) {
            return  new Bike(SlotType.BIKE, registrationNum, color);
        }
        return  new Truck(SlotType.BIKE, registrationNum, color);
    }

    public void parkVehicle(ParkingArena parkingArena, String vehicleType, String registrationNum, String color) {
        IVehicle incomingVehicle = getVehicleBasedOnType(vehicleType,registrationNum,color);
        boolean isVehicleParked = false;
        Ticket ticket = null;
        for (int floor = 0; floor < parkingArena.getNoOfFloors(); floor++) {
            Floor floorN = parkingArena.getFloors()[floor];
            int vacantSlot = floorN.getVacantSlotBasedOnType(vehicleType);
            if (vacantSlot > 0) {
                isVehicleParked = true;
                VehicleSlot[] slots = floorN.getSlots();

                int slotSearchPos = floorN.getVehicleStartPositionBasedOnType(vehicleType);
                int noOfVehicles = floorN.getVehicleCountBasedOnType(vehicleType);

                for (int slot = slotSearchPos; slot <= slotSearchPos + noOfVehicles - 1; slot++) {
                    if (!slots[slot - 1].hasVehicleOccupied()) {
                        slots[slot - 1].addVehicle(incomingVehicle);
                        ticket = new Ticket(parkingArena.getParkingArenaId(), floor + 1, slot);
                        ticketMap.put(ticket.getTicketId(), ticket);
                        floorN.vehicleAddedToFloor(vehicleType);
                        break;
                    }
                }
                break;
            }
        }
        if (isVehicleParked) {
            assert ticket != null;
            System.out.println("Parked vehicle. Ticket ID: " + ticket.getTicketId());
        } else {
            System.out.println("Parking Lot Full");
        }
    }

    public void unParkVehicle(ParkingArena parkingArena, String ticketId) {
        if (ticketMap.containsKey(ticketId)) {
            Ticket ticket = ticketMap.get(ticketId);
            int floor = ticket.getFloorId() - 1;
            int slotPos = ticket.getSlotId() - 1;
            Floor floorN = parkingArena.getFloors()[floor];
            VehicleSlot slot = floorN.getSlots()[slotPos];
            IVehicle vehicle = slot.getVehicle();

            System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegistrationNum() + " and Color: " + vehicle.getColor());

            slot.removeVehicle();
            SlotType vehicleType = vehicle.getSlotType();
            floorN.vehicleLeftFromFloor(vehicleType);
            ticketMap.remove(ticketId);
        } else {
            System.out.println("Invalid Ticket");
        }
    }

    public void displayFreeCount(ParkingArena parkingArena, String vehicleType) {
        for (int floor = 0; floor < parkingArena.getNoOfFloors(); floor++) {
            Floor floorN = parkingArena.getFloors()[floor];
            int vacantSlot = floorN.getVacantSlotBasedOnType(vehicleType);
//            String vacantSlotString = vacantSlot == 0 ? "" : String.valueOf(vacantSlot);
            System.out.println("No. of free slots for " + vehicleType + " on Floor " + (floor + 1) + ": " + vacantSlot);
        }
    }

    public void displayFreeSlots(ParkingArena parkingArena, String vehicleType) {
        for (int floor = 0; floor < parkingArena.getNoOfFloors(); floor++) {
            Floor floorN = parkingArena.getFloors()[floor];
            VehicleSlot[] slots = floorN.getSlots();

            int slotSearchPos = floorN.getVehicleStartPositionBasedOnType(vehicleType);
            int noOfVehicles = floorN.getVehicleCountBasedOnType(vehicleType);

            StringBuilder freeSlots = new StringBuilder();
            for (int slot = slotSearchPos; slot <= slotSearchPos + noOfVehicles - 1; slot++) {
                if (!slots[slot - 1].hasVehicleOccupied()) {
                    if (freeSlots.length() == 0) {
                        freeSlots.append(slot);
                    } else {
                        freeSlots.append(", " + slot);
                    }
                }
            }
            System.out.println("Free slots for " + vehicleType + " on Floor " + (floor + 1) + ": " + freeSlots);
        }
    }

    public void displayOccupiedSlots(ParkingArena parkingArena, String vehicleType) {
        for (int floor = 0; floor < parkingArena.getNoOfFloors(); floor++) {
            Floor floorN = parkingArena.getFloors()[floor];
            VehicleSlot[] slots = floorN.getSlots();

            int slotSearchPos = floorN.getVehicleStartPositionBasedOnType(vehicleType);
            int noOfVehicles = floorN.getVehicleCountBasedOnType(vehicleType);

            StringBuilder freeSlots = new StringBuilder();
            for (int slot = slotSearchPos; slot <= slotSearchPos + noOfVehicles - 1; slot++) {
                if (slots[slot - 1].hasVehicleOccupied()) {
                    if (freeSlots.length() == 0) {
                        freeSlots.append(slot);
                    } else {
                        freeSlots.append(", " + slot);
                    }
                }
            }
            System.out.println("Occupied slots for " + vehicleType + " on Floor " + (floor + 1) + ": " + freeSlots);
        }
    }

}
