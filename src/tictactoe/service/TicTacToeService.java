package tictactoe.service;

import tictactoe.modal.Player;
import tictactoe.modal.TicTacToeBoard;
import tictactoe.modal.TicTacToeTile;

public class TicTacToeService {

    public void addPlayerToBoard(TicTacToeBoard ticTacToeBoard, Player player) {
        ticTacToeBoard.addPlayer(player);
    }

    public void printBoard(TicTacToeBoard ticTacToeBoard) {
        ticTacToeBoard.printBoard();
    }

    public String setPieceInPosition(TicTacToeBoard ticTacToeBoard, int positionX, int positionY) {
        Player player = ticTacToeBoard.nextPlayerToPlay();
        TicTacToeTile ticTacToeTile = ticTacToeBoard.getBoardTile(positionX - 1, positionY - 1);
        if (ticTacToeTile == null || ticTacToeTile.getTilePiece() != '-') {
            ticTacToeBoard.playerPlayedAnInvalidMove();
            return "Invalid Move";
        }

        ticTacToeTile.setTilePiece(player.getPieceName());
        ticTacToeBoard.recordTilePieceAdded();
        boolean winningStatus = ticTacToeBoard.checkIfPlayerWon(positionX - 1, positionY - 1, player.getPieceName());
        if ( winningStatus )
            return player.getPlayerName() + " won the game";

        if ( ticTacToeBoard.isAllTilesFilled() ) {
            return "Game Over";
        }

        return "Continue Game";
    }

}
