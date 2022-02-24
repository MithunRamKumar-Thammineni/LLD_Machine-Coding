package TicTacToe.Validator;

import TicTacToe.Model.Board;

import java.util.HashMap;
import java.util.Map;

public class GameValidator {
    Board board;
    Map<Integer, Boolean> playercheck;

    public GameValidator(Board board, HashMap<Integer, Boolean> playercheck) {
        this.board = board;
        this.playercheck = playercheck;
    }

    public boolean validateCoordinates(int x, int y) {
        return x < board.getBoard().length && y < board.getBoard().length
                && x >= 0 && y >= 0;
    }

    public boolean validateisboardposempty(int x, int y) {
        return board.getPosition(x, y).equals("-");
    }
}
