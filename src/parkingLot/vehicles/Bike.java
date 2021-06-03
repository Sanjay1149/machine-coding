package parkingLot.vehicles;

public class Bike extends IVehicle {

    public Bike(SlotType slotType, String registrationNum, String color) {
        super(slotType,registrationNum,color);
    }

    @Override
    public void checkVehicleInfo() {

    }

    @Override
    public  SlotType getVehicleType() {
        return SlotType.BIKE;
    }
}
