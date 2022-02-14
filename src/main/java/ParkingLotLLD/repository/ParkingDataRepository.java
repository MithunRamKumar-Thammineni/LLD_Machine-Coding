package ParkingLotLLD.repository;


import ParkingLotLLD.entity.ParkingFloor;
import ParkingLotLLD.entity.ParkingSlot;
import ParkingLotLLD.entity.VehicleType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingDataRepository {
    List<ParkingFloor> parkingFloordata;
    List<ParkingSlot> parkingSlotsdata;

    public List<ParkingFloor> initialize_data(int floors, int slots) {
        parkingFloordata = new ArrayList<>(floors);
        for (int i = 0; i < floors; i++) {
            initialize_slots(slots);
            List<ParkingSlot> parkingSlots = getparkingslotdata(i);
            parkingFloordata.add(new ParkingFloor(parkingSlots));
        }
        return parkingFloordata;
    }

    private List<ParkingSlot> getparkingslotdata(int floor_id) {
        for (ParkingSlot parkingSlot : parkingSlotsdata) {
            parkingSlot.setFloor_id(floor_id);
        }
        return parkingSlotsdata;
    }

    private void initialize_slots(int slots) {
        parkingSlotsdata = new ArrayList<>();
        if (slots >= 1) {
            parkingSlotsdata.add(addtruckdata());
        }  if (slots >= 3) {
            for (int i = 1; i < 3; i++)
                parkingSlotsdata.add(addbikedata(i));
        }  if (slots > 3) {
            for (int i = 3; i < slots; i++)
                parkingSlotsdata.add(addcardata(i));

        }
    }

    private ParkingSlot addcardata(int slot_id) {
        return new ParkingSlot(slot_id, VehicleType.CAR, true);
    }

    private ParkingSlot addbikedata(int slot_id) {
        return new ParkingSlot(slot_id, VehicleType.BIKE, true);
    }

    private ParkingSlot addtruckdata() {
        return new ParkingSlot(0, VehicleType.TRUCK, true);
    }
}
