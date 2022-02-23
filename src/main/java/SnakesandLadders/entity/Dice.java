package SnakesandLadders.entity;

public class Dice {
    private int numberofDice;

    public Dice(int numberofDice) {
        this.numberofDice = numberofDice;
    }

    public int rollDice() {
        return ((int) (Math.random() * (6 * numberofDice - 1 * numberofDice))) + 1;

    }
}
