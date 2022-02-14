package ParkingLotLLD.service;

import ParkingLotLLD.entity.*;
import ParkingLotLLD.repository.ParkingDataRepository;

import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotService {
    ParkingLot parkingLot;
    ParkingDataRepository parkingDataRepository;
    HashMap<String, Vehicle> vehicleHashMap;

    public void create_parking_lot(ParkingLot parkingLot) {
        vehicleHashMap = new HashMap<>();
        parkingDataRepository = new ParkingDataRepository();
        this.parkingLot = parkingLot;
        parkingLot.setParkingFloorList(parkingDataRepository.initialize_data(parkingLot.getNooffloors(), parkingLot.getNoofslots()));
        System.out.println("Created parking lot with " + parkingLot.getNooffloors() + " floors and " + parkingLot.getNoofslots() + " slots per floor");

    }

    public void park_vehicle(VehicleType vehicleType, String reg_no, String color) {
        ParkingSlot parkingSlot = getavailablefreeslot(vehicleType);
        if (parkingSlot != null) {
            parkingSlot.setIs_free(false);
            Vehicle vehicle = new Vehicle(vehicleType, reg_no, color, parkingSlot);
            String ticket = generate_ticket(parkingSlot, vehicle);
            vehicle.setTicket(ticket);
            vehicleHashMap.put(ticket, vehicle);
            System.out.println("Parked Vehicle->Ticked Id" + ticket);

        }
    }

    public void unpark_vehicle(String reg_no) {
        if (vehicleHashMap.get(reg_no) != null) {
            Vehicle vehicle = vehicleHashMap.get(reg_no);
            ParkingSlot parkingSlot = vehicle.getParkingSlot();
            parkingSlot.setIs_free(true);
            vehicleHashMap.remove(reg_no);
            parkingLot.getParkingFloorList().get(parkingSlot.getFloor_id()).getParkingSlots().get(parkingSlot.getSlot_id()).setIs_free(true);
            System.out.println("Unparked vehicle with Registration Number:" + vehicle.getReg_no() + " and Color:" + vehicle.getColor());
        } else
            System.out.println("Invalid Ticket");
    }

    public void display(DisplayType displayType, VehicleType vehicleType) {
        if (displayType.equals(DisplayType.FREE_COUNT)) {
            displayfreecount(vehicleType);
        } else if (displayType.equals(DisplayType.FREE_SLOTS)) {
            diplayfreeslots(vehicleType);

        } else if (displayType.equals(DisplayType.OCCUPIED_SLOTS)) {
            displayoccupiedslots(vehicleType);
        }

    }

    private void displayoccupiedslots(VehicleType vehicleType) {
        for (int i = 0; i < parkingLot.getNooffloors(); i++) {
            ParkingFloor parkingFloor = parkingLot.getParkingFloorList().get(i);
            List<ParkingSlot> collect = parkingFloor.getParkingSlots().stream().filter(parkingSlot -> parkingSlot.getVehicleType().equals(vehicleType)
                    && !parkingSlot.isIs_free()).collect(Collectors.toList());
            StringBuilder sb = new StringBuilder();
            for (ParkingSlot parkingSlot : collect) {
                sb.append(parkingSlot.getSlot_id() + 1);
                sb.append(",");
            }
            System.out.println(sb.toString());

        }
    }

    private void diplayfreeslots(VehicleType vehicleType) {
        for (int i = 0; i < parkingLot.getNooffloors(); i++) {
            ParkingFloor parkingFloor = parkingLot.getParkingFloorList().get(i);
            List<ParkingSlot> collect = parkingFloor.getParkingSlots().stream().filter(parkingSlot -> parkingSlot.getVehicleType().equals(vehicleType)
                    ).filter(parkingSlot -> parkingSlot.isIs_free()).collect(Collectors.toList());
            StringBuilder sb = new StringBuilder("no of free slots for " + vehicleType + "on Floor " + (i + 1) + ":");
            for (ParkingSlot parkingSlot : collect) {
                sb.append(parkingSlot.getSlot_id() + 1);
                sb.append(",");
            }
            System.out.println(sb.toString());
        }
    }

    private void displayfreecount(VehicleType vehicleType) {
        for (int i = 0; i < parkingLot.getNooffloors(); i++) {
            ParkingFloor parkingFloor = parkingLot.getParkingFloorList().get(i);
            long count = parkingFloor.getParkingSlots().stream().filter(parkingSlot -> parkingSlot.getVehicleType().equals(vehicleType)
                    && parkingSlot.isIs_free()).count();
            System.out.println("No of free slots for " + vehicleType + "on floor" + (i + 1) + ":" + count);
        }
    }

    private String generate_ticket(ParkingSlot parkingSlot, Vehicle vehicle) {
        StringBuilder sb = new StringBuilder(parkingLot.getParking_lot_id() + parkingSlot.getFloor_id() + parkingSlot.getSlot_id());
        return sb.toString();

    }

    private ParkingSlot getavailablefreeslot(VehicleType vehicleType) {
        List<ParkingFloor> parkingFloorList = parkingLot.getParkingFloorList();
        for (ParkingFloor parkingFloor : parkingFloorList) {
            List<ParkingSlot> parkingSlots = getAvailableparkingslots(parkingFloor.getParkingSlots(), vehicleType);
            for (ParkingSlot parkingSlot : parkingSlots) {
                if (parkingSlot.isIs_free())
                    return parkingSlot;
            }
        }
        return null;
    }

    private List<ParkingSlot> getAvailableparkingslots(List<ParkingSlot> parkingSlots, VehicleType vehicleType) {
        if (vehicleType.equals(VehicleType.TRUCK) && parkingSlots.size() >= 1) {
            return Arrays.asList(parkingSlots.get(0));
        } else if (vehicleType.equals(VehicleType.BIKE) && parkingSlots.size() >= 3) {
            return Arrays.asList(parkingSlots.get(1), parkingSlots.get(2));
        } else if (vehicleType.equals(VehicleType.CAR) && parkingSlots.size() > 3) {
            return parkingSlots;
        }
        return null;
    }
}
