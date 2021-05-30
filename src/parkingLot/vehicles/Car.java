package parkingLot.vehicles;

public class Car implements IVehicle {
    SlotType slotType;
    String registrationNum;
    String color;

    public Car(SlotType slotType, String registrationNum, String color) {
        this.slotType = slotType;
        this.registrationNum = registrationNum;
        this.color = color;
    }

    @Override
    public void checkVehicleInfo() {

    }

    @Override
    public SlotType getVehicleType() {
        return SlotType.CAR;
    }

    @Override
    public SlotType getSlotType() {
        return slotType;
    }

    @Override
    public String getRegistrationNum() {
        return registrationNum;
    }

    @Override
    public String getColor() {
        return color;
    }

}
