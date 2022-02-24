package TicTacToe.Service;

import TicTacToe.Exceptions.BoardException;
import TicTacToe.Model.Board;
import TicTacToe.Model.Player;
import TicTacToe.Validator.GameValidator;

import java.util.List;

public class TicTacToeService {
    Board board;
    List<Player> players;
    GameValidator gameValidator;
    int gamecount = 0;

    public TicTacToeService(Board board, List<Player> players, GameValidator gameValidator) {
        this.board = board;
        this.players = players;
        this.gameValidator = gameValidator;

    }

    public void input(int x, int y, Player player) {
        if (!gameValidator.validateCoordinates(x, y))
            throw new BoardException("Inputs are not valid");
        if (!gameValidator.validateisboardposempty(x, y))
            throw new BoardException("Board point already filled");
        String symbol = player.getSymbol();

        board.SetPosition(x, y, symbol);
        System.out.println(board);

        if (checkboard(x, y, symbol)) {
            System.out.println("player" + player.getPlayer_name() + "wins");
            System.exit(0);
        }
        gamecount++;
        if (checkBoardFill()) {
            System.out.println("Game Draw");
            System.exit(0);
        }


    }

    private boolean checkboard(int row, int col, String symbol) {
        boolean winrow = true, wincol = true, winleft = true, winright = true;
        for (int i = 0; i < board.getBoard().length; i++) {
            if (!board.getPosition(row, i).equals(symbol))
                winrow = false;
            if (!board.getPosition(i, col).equals(symbol))
                wincol = false;
            if (!board.getPosition(i, i).equals(symbol))
                winleft = false;
            if (!board.getPosition(i, board.getBoard().length - i - 1).equals(symbol))
                winright = false;
        }
        return winrow || wincol || winleft || winright;
    }

    private boolean checkBoardFill() {
        return gamecount == board.getBoard().length * board.getBoard()[0].length;
    }

}
