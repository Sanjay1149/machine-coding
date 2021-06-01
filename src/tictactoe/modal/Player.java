package tictactoe.modal;

public class Player {
    String playerId;
    static int playerCount;
    String playerName;
    char pieceName;

    public String getPlayerName() {
        return playerName;
    }

    public Player(String playerName) {
        this.playerName = playerName;
        playerId = this.createPlayerId();
    }

    public String getPlayerId() {
        return playerId;
    }

    private String createPlayerId() {
        return "player_" + ++playerCount;
    }

    public char getPieceName() {
        return pieceName;
    }

    public void setPieceName(char pieceName) {
        this.pieceName = pieceName;
    }
}
