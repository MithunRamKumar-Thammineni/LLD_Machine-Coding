package ParkingLotLLD;

import ParkingLotLLD.entity.Command;
import ParkingLotLLD.entity.DisplayType;
import ParkingLotLLD.entity.ParkingLot;
import ParkingLotLLD.entity.VehicleType;
import ParkingLotLLD.service.ParkingLotService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ParkingLotService parkingLotService = new ParkingLotService();
        Scanner scn = new Scanner(System.in);
        while (true) {
            Command type = Command.of(scn.next());
            switch (type) {
                case CREATE_PARKING_LOT:
                    parkingLotService.create_parking_lot(new ParkingLot(scn.next(), scn.nextInt(), scn.nextInt()));
                    break;
                case PARK_VEHICLE:
                    parkingLotService.park_vehicle(VehicleType.valueOf(scn.next()), scn.next(), scn.next());
                    break;
                case UNPARK_VEHICLE:
                    parkingLotService.unpark_vehicle(scn.next());
                    break;
                case DISPLAY:
                    parkingLotService.display(DisplayType.of(scn.next()), VehicleType.valueOf(scn.next()));
                    break;
                case EXIT:
                    return;
            }
        }
    }
}
