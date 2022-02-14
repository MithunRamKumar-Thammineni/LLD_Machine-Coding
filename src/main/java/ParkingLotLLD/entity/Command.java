package ParkingLotLLD.entity;

import java.util.HashMap;

public enum Command {
    CREATE_PARKING_LOT("create_parking_lot"),
    DISPLAY("display"),
    PARK_VEHICLE("park_vehicle"),
    UNPARK_VEHICLE("unpark_vehicle"),
    EXIT("exit");

    private String command;

    Command(String s) {
        command = s;
    }

    private static HashMap<String, Command> map = new HashMap<>(values().length, 1);

    static {
        for (Command c : values())
            map.put(c.command, c);
    }

    public static Command of(String s) {
        return map.get(s);
    }
}
