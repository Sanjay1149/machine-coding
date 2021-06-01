package tictactoe;

import tictactoe.modal.Player;
import tictactoe.modal.TicTacToeBoard;
import tictactoe.service.TicTacToeService;

import java.util.Scanner;

public class TicTacToe {

    TicTacToeBoard ticTacToeBoard;
    TicTacToeService ticTacToeService;

    public static void main(String args[]) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.ticTacToeBoard = new TicTacToeBoard(3,3,3,2);
        ticTacToe.ticTacToeService = new TicTacToeService();
        ticTacToe.createStimulation();

    }

    void createStimulation() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String[] inputArray = in.nextLine().split(" ");
            if ( inputArray[0].equals("exit") ) {
                break;
            } else if ( inputArray[0].chars().allMatch( Character::isDigit ) ) {
                int positionX = Integer.parseInt(inputArray[0]);
                int positionY = Integer.parseInt(inputArray[1]);
                String playerMove = ticTacToeService.setPieceInPosition(ticTacToeBoard, positionX, positionY);
                if ( playerMove.equals("Game Over")) {
                    ticTacToeBoard.printBoard();
                    System.out.println("Game Over");
                    break;
                } else if ( playerMove.equals("Invalid Move") ) {
                    System.out.println("Invalid Move");
                } else {
                    ticTacToeBoard.printBoard();
                    if ( playerMove.contains("won") ) {
                        System.out.println(playerMove);
                        break;
                    }
                }
            } else {
                /**
                 * If the first value is not a digit
                 */
                Player player = new Player(inputArray[1]);
                player.setPieceName(inputArray[0].charAt(0));
                ticTacToeService.addPlayerToBoard(ticTacToeBoard, player);
            }
        }
    }
}
