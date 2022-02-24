package TicTacToe.Model;

import java.util.Arrays;

public class Board {
    String[][] board;

    public Board(int n) {
        board = new String[n][n];
        for (String str[] : board)
            Arrays.fill(str, "-");
    }

    public void SetPosition(int x, int y, String Symbol) {
        board[x][y] = Symbol;
    }

    public String getPosition(int x, int y) {
        return board[x][y];
    }

    public String[][] getBoard() {
        return board;
    }
}
