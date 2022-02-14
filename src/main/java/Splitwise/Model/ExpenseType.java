package Splitwise.Model;

import java.util.HashMap;

public enum ExpenseType {
    EQUAL("EQUAL"),
    EXCAT("EXCAT"),
    PERCENT("PERCENT");

    private final String expense;

    ExpenseType(String exp) {
        expense = exp;
    }

    private static HashMap<String, ExpenseType> map = new HashMap<>(values().length, 1);

    static {
        for (ExpenseType c : values()) {
            map.put(c.expense, c);
        }
    }

    public static ExpenseType of(String expense) {
        return map.get(expense);
    }
}
