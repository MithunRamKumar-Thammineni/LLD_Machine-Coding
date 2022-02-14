package ParkingLotLLD.entity;

public class Vehicle {
    VehicleType vehicleType;
    String reg_no;
    String color;
    ParkingSlot parkingSlot;
    String ticket;

    public Vehicle(VehicleType vehicleType, String reg_no, String color, ParkingSlot parkingSlot) {
        this.vehicleType = vehicleType;
        this.reg_no = reg_no;
        this.color = color;
        this.parkingSlot = parkingSlot;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
