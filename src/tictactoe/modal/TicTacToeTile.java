package tictactoe.modal;

public class TicTacToeTile {
    String tileId;
    int positionX;
    int positionY;
    private char tilePiece;

    TicTacToeTile(int positionX, int positionY) {
        tilePiece = '-';
        this.positionX = positionX;
        this.positionY = positionY;
        this.tileId = this.createTileId();
    }

    private String createTileId() {
        return "tile_"+ positionX + "_" + positionY;
    }

    public char getTilePiece() {
        return tilePiece;
    }

    public void setTilePiece(char tilePiece) {
        this.tilePiece = tilePiece;
    }
}
