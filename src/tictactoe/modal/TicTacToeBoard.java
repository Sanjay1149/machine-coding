package tictactoe.modal;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeBoard {
    String boardId;
    int boardSizeX;
    int boardSizeY;
    int strikeCount;
    int currentPlayer = 1;
    int playerCount;
    int tilesOccupied = 0;
    TicTacToeTile[][] boardTiles;
    Map<String, Player> playerMap;

    public TicTacToeBoard(int boardSizeX, int boardSizeY, int strikeCount, int playerCount) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.strikeCount = strikeCount;
        this.playerCount = playerCount;
        this.createBoard(playerCount);
    }

    private void createBoard(int playerCount) {
        this.boardTiles = new TicTacToeTile[boardSizeX][boardSizeY];
        for (int i = 0; i < boardSizeX; i++) {
            for (int j = 0; j < boardSizeY; j++) {
                this.boardTiles[i][j] = new TicTacToeTile(i, j);
            }
        }
        this.playerMap = new HashMap<>();
        this.boardId = this.createBoardId();
    }

    private String createBoardId() {
        return "board_" + boardSizeX + "_" + boardSizeY;
    }

    public void addPlayer(Player player) {
        playerMap.put(player.getPlayerId(), player);
    }

    public Player nextPlayerToPlay() {
        Player player = playerMap.get("player_" + currentPlayer);
        currentPlayer = (currentPlayer + 1 > playerCount) ? 1 : currentPlayer + 1;
        return player;
    }

    public void playerPlayedAnInvalidMove() {
        currentPlayer -= 1;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < boardSizeX; i++) {
            for (int j = 0; j < boardSizeY; j++) {
                System.out.print(boardTiles[i][j].getTilePiece() + " ");
            }
            System.out.println();
        }
    }

    public TicTacToeTile getBoardTile(int positionX, int positionY) {
        if (positionX >= 0 && positionX < this.boardSizeX && positionY >= 0 && positionY < this.boardSizeY)
            return boardTiles[positionX][positionY];
        return null;
    }

    public void recordTilePieceAdded() {
        tilesOccupied+=1;
    }

    public boolean isAllTilesFilled() {
        return tilesOccupied == boardSizeX * boardSizeY;
    }

    public boolean checkIfPlayerWon(int positionX, int positionY, char tilePiece) {
        boolean didPlayerWin = false;
        /**
         * Checking Right & Left Strike
         */
        int currentStrikeCount = 0;
        currentStrikeCount = checkLeftRightStrikeCount(positionX, positionY, tilePiece, currentStrikeCount);
        if (currentStrikeCount >= strikeCount)
            return true;

        /**
         * Checking Top & Down Strike
         */
        currentStrikeCount = 0;
        currentStrikeCount = checkTopDownStrikeCount(positionX, positionY, tilePiece, currentStrikeCount);
        if (currentStrikeCount >= strikeCount)
            return true;

        /**
         * Checking Diagonal Strike
         */
        currentStrikeCount = 0;
        currentStrikeCount = checkDiagonalStrikeCount(positionX, positionY, tilePiece, currentStrikeCount);
        if (currentStrikeCount >= strikeCount)
            return true;

        return false;
    }

    private int checkDiagonalStrikeCount(int positionX, int positionY, char tilePiece, int currentStrikeCount) {
        for (int i = positionX, j = positionY; i < boardSizeX && j < boardSizeY; i++, j++) {
            if (boardTiles[i][j].getTilePiece() == tilePiece) {
                currentStrikeCount++;
            } else {
                break;
            }
        }
        for (int i = positionX - 1, j = positionY - 1; i >= 0 && j >= 0; i--, j--) {
            if (boardTiles[i][j].getTilePiece() == tilePiece) {
                currentStrikeCount++;
            } else {
                break;
            }
        }
        return currentStrikeCount;
    }

    private int checkTopDownStrikeCount(int positionX, int positionY, char tilePiece, int currentStrikeCount) {
        for (int i = positionX; i < boardSizeX; i++) {
            if (boardTiles[i][positionY].getTilePiece() == tilePiece) {
                currentStrikeCount++;
            } else {
                break;
            }
        }
        for (int i = positionX - 1; i >= 0; i--) {
            if (boardTiles[i][positionY].getTilePiece() == tilePiece) {
                currentStrikeCount++;
            } else {
                break;
            }
        }
        return currentStrikeCount;
    }

    private int checkLeftRightStrikeCount(int positionX, int positionY, char tilePiece, int currentStrikeCount) {
        for (int j = positionY; j < boardSizeY; j++) {
            if (boardTiles[positionX][j].getTilePiece() == tilePiece) {
                currentStrikeCount++;
            } else {
                break;
            }
        }
        for (int j = positionY - 1; j >= 0; j--) {
            if (boardTiles[positionX][j].getTilePiece() == tilePiece) {
                currentStrikeCount++;
            } else {
                break;
            }
        }
        return currentStrikeCount;
    }

}
