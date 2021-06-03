package parkingLot.vehicles;

public abstract class IVehicle {
    SlotType slotType;
    String registrationNum;
    String color;

    public IVehicle(SlotType slotType, String registrationNum, String color) {
        this.slotType = slotType;
        this.registrationNum = registrationNum;
        this.color = color;
    }

    abstract void checkVehicleInfo();

    abstract SlotType getVehicleType();

    public SlotType getSlotType() {
        return slotType;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public String getColor() {
        return color;
    }
}
