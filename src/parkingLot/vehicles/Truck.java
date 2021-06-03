package parkingLot.vehicles;

public class Truck extends IVehicle {

    public Truck(SlotType slotType, String registrationNum, String color) {
        super(slotType,registrationNum,color);
    }

    @Override
    public void checkVehicleInfo() {

    }

    @Override
    public  SlotType getVehicleType() {
        return SlotType.TRUCK;
    }
}
