package parkingLot.modal;

public class Ticket {
    String ticketId;
    String parkingArenaId;
    int floorId;
    int slotId;

    public Ticket(String parkingArenaId, int floorId, int slotId) {
        this.parkingArenaId = parkingArenaId;
        this.floorId = floorId;
        this.slotId = slotId;
        ticketGeneration(parkingArenaId, floorId, slotId);
    }

    void ticketGeneration(String parkingArenaId, int floorId, int slotId) {
        this.ticketId = parkingArenaId + "_" + floorId + "_" + slotId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getParkingArenaId() {
        return parkingArenaId;
    }

    public int getFloorId() {
        return floorId;
    }

    public int getSlotId() {
        return slotId;
    }
}
