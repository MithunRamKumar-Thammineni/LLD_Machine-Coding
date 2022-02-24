package TicTacToe;

import TicTacToe.Model.Board;
import TicTacToe.Model.Player;
import TicTacToe.Service.TicTacToeService;
import TicTacToe.Validator.GameValidator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        Board board = new Board(3);
        Player player1 = new Player(1, "player1", "0");
        Player player2 = new Player(2, "player2", "X");

        List<Player> players = Arrays.asList(player1, player2);
        HashMap<Integer, Boolean> playercheck = new HashMap<>();
        playercheck.put(player1.getId(), true);
        playercheck.put(player2.getId(), true);

        GameValidator gameValidator = new GameValidator(board, playercheck);
        TicTacToeService ticTacToeService = new TicTacToeService(board, players, gameValidator);

        while (true) {
            System.out.println("Choose input 1:play 2:exit");
            int option = scn.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter your id");
                    Player player = players.get(scn.nextInt() - 1);
                    System.out.println("Enter Move of x and Y:");
                    ticTacToeService.input(scn.nextInt(), scn.nextInt(), player);
                    break;

                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose 1");
                    break;
            }

        }
    }

}
