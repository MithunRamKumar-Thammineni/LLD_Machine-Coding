package Splitwise.Model;

import java.util.HashMap;

public enum Command {
    SHOW("SHOW"),
    EXPENSE("EXPENSE"),
    QUIT("QUIT");

    private final String command;

    Command(String s) {
        command = s;
    }

    private static HashMap<String, Command> map = new HashMap<>(values().length, 1);

    static {
        for (Command c : map.values())
            map.put(c.command, c);
    }

    public static Command of(String s) {
        return map.get(s);
    }
}
