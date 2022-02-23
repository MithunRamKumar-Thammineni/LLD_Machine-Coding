package SnakesandLadders.Service;

import SnakesandLadders.entity.Dice;
import SnakesandLadders.entity.Jumper;
import SnakesandLadders.entity.Player;


import java.util.*;

public class GameBoard {
    private Dice dice;
    private Queue<Player> nexturn;
    private List<Jumper> snakes;
    private List<Jumper> ladders;
    private Map<String, Integer> playercurrentpos;
    int boardsize;

    public GameBoard(Dice dice, Queue<Player> nexturn, List<Jumper> snakes, List<Jumper> ladders, Map<String, Integer> playercurrentpos, int boardsize) {
        this.dice = dice;
        this.nexturn = nexturn;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playercurrentpos = playercurrentpos;
        this.boardsize = boardsize;
    }

    public void startgame() {
        while (nexturn.size() > 1) {
            Player player = nexturn.remove();
            int currentpostion = playercurrentpos.get(player.getName());
            int dicevalue = dice.rollDice();
            int nextcell = currentpostion + dicevalue;
            if (nextcell > boardsize)
                nexturn.add(player);
            else if (nextcell == boardsize)
                System.out.println(player.getName() + "wins the game");
            else {
                int[] nextpos = new int[1];
                boolean b[] = new boolean[1];
                nextpos[0] = nextcell;
                //for checking if its a snake
                snakes.forEach(v -> {
                    if (v.getStartpoint() == nextcell)
                        nextpos[0] = v.getEndpoint();
                });

                if (nextpos[0] != nextcell)
                    System.out.println(player.getName() + "Bitten by a snake");

                snakes.forEach(v -> {
                    if (v.getStartpoint() == nextcell)
                        nextpos[0] = v.getEndpoint();
                    b[0] = true;
                });
                if (nextcell != nextpos[0] && b[0])
                    System.out.println(player.getName() + "got a ladder at" + nextcell);
                if (nextpos[0] == boardsize)
                    System.out.println(player.getName() + "has won the game");
                else {
                    playercurrentpos.put(player.getName(), nextpos[0]);
                    System.out.println(player.getName() + "is at position" + nextpos[0]);
                    nexturn.add(player);
                }
            }

        }
    }


}
