package parkingLot.vehicles;

public interface IVehicle {

    void checkVehicleInfo();

    SlotType getVehicleType();

    SlotType getSlotType();

    String getRegistrationNum();

    String getColor();
}
