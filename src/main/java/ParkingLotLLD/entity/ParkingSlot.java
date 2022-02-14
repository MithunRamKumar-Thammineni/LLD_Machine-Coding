package ParkingLotLLD.entity;

public class ParkingSlot {
    int floor_id;
    int slot_id;
    VehicleType vehicleType;
    boolean is_free;
    Vehicle vehicle;

    //Vehicle
    public ParkingSlot(int slot_id, VehicleType vehicleType, boolean is_free) {
        this.slot_id = slot_id;
        this.vehicleType = vehicleType;
        this.is_free = is_free;
    }

    public int getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(int floor_id) {
        this.floor_id = floor_id;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
