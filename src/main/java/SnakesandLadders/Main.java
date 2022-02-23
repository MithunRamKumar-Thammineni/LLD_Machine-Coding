package SnakesandLadders;

import SnakesandLadders.Service.GameBoard;
import SnakesandLadders.entity.Dice;
import SnakesandLadders.entity.Jumper;
import SnakesandLadders.entity.Player;

import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int noofsnakes = scn.nextInt();
        List<Jumper> snakes = new ArrayList<>();
        List<Jumper> ladders = new ArrayList<>();
        Queue<Player> players = new LinkedList<>();
        Map<String, Integer> playerpos = new HashMap<>();
        for (int i = 0; i < noofsnakes; i++) {
            int sp = scn.nextInt();
            int ep = scn.nextInt();
            snakes.add(new Jumper(sp, ep));
        }
        int noonladders = scn.nextInt();
        for (int i = 0; i < noonladders; i++) {
            int sp = scn.nextInt();
            int ep = scn.nextInt();
            ladders.add(new Jumper(sp, ep));
        }
        int noofplayers = scn.nextInt();
        for (int i = 0; i < noofplayers; i++) {
            String player_name = scn.next();
            players.add(new Player(player_name));
            playerpos.put(player_name, 0);
        }
        Dice dice = new Dice(1);
        GameBoard gb = new GameBoard(dice, players, snakes, ladders, playerpos, 100);
        gb.startgame();
    }
}
