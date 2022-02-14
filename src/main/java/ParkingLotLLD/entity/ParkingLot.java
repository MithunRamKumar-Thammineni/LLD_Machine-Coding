package ParkingLotLLD.entity;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    List<ParkingFloor> parkingFloorList;
    int nooffloors;
    int noofslots;
    String parking_lot_id;

    public ParkingLot(String parking_lot_id, int nooffloors, int noofslots) {
        this.parking_lot_id = parking_lot_id;
        this.nooffloors = nooffloors;
        this.noofslots = noofslots;
        this.parkingFloorList = new ArrayList<>(nooffloors);
    }

    public List<ParkingFloor> getParkingFloorList() {
        return parkingFloorList;
    }

    public void setParkingFloorList(List<ParkingFloor> parkingFloorList) {
        this.parkingFloorList = parkingFloorList;
    }

    public int getNooffloors() {
        return nooffloors;
    }

    public void setNooffloors(int nooffloors) {
        this.nooffloors = nooffloors;
    }

    public int getNoofslots() {
        return noofslots;
    }

    public void setNoofslots(int noofslots) {
        this.noofslots = noofslots;
    }

    public String getParking_lot_id() {
        return parking_lot_id;
    }

    public void setParking_lot_id(String parking_lot_id) {
        this.parking_lot_id = parking_lot_id;
    }
}
