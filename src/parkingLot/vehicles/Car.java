package parkingLot.vehicles;

public class Car extends IVehicle {

    public Car(SlotType slotType, String registrationNum, String color) {
        super(slotType,registrationNum,color);
    }

    @Override
    public void checkVehicleInfo() {

    }

    @Override
    public  SlotType getVehicleType() {
        return SlotType.CAR;
    }

}
