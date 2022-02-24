package TicTacToe.Model;

public class Player {
    private int id;
    private String player_name;
    private String symbol;
    private boolean is_win = false;

   public Player(int id, String player_name, String symbol) {
        this.id = id;
        this.player_name = player_name;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isIs_win() {
        return is_win;
    }
}
