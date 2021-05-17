package snakeAndLadder;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

class Snake {
    int head;
    int tail;

    Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }
}

class Ladder {
    int end;
    int start;

    Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class BoardPosition {
    Ladder ladder;
    Snake snake;

    BoardPosition(Ladder ladder) {
        this.ladder = ladder;
    }

    BoardPosition(Snake snake) {
        this.snake = snake;
    }

    public BoardPosition() {
        this.ladder = null;
        this.snake = null;
    }
}

enum Color {
    RED, GREEN, BLUE, YELLOW, PURPLE, WHITE;
}

class Player {
    Color playerColor;
    int playerPosition;
    String name;

    Player(String name) {
        this.name = name;
        playerPosition = 0;
    }
}

public class SnakeLadder {
    static BoardPosition[] board = new BoardPosition[101];

    static String movedToPosition(Player player, int diceRolled) {
        int playerCurrentPosition = player.playerPosition;
//        MovePlayerPosition movePlayerPosition = null;
        String moveReason = "";
        int moveToPosition = playerCurrentPosition + diceRolled;
        moveReason = "moved from " + playerCurrentPosition + " to " + moveToPosition;

        if (moveToPosition > 100) {
            moveToPosition = player.playerPosition;
            moveReason = "rolled dice exceeded 100, so loses the turn";
//            movePlayerPosition = new MovePlayerPosition(moveToPosition, moveReason);
        } else if (board[moveToPosition].snake != null) {
            moveToPosition = board[moveToPosition].snake.tail;
            moveReason += " but got bitten by the snake and dragged to " + moveToPosition;
//            movePlayerPosition = new MovePlayerPosition(moveToPosition, moveReason);
        } else if (board[moveToPosition].ladder != null) {
            moveToPosition = board[moveToPosition].ladder.end;
            moveReason += " caught the ladder and rised to " + moveToPosition;
//            movePlayerPosition = new MovePlayerPosition(moveToPosition, moveReason);
        }

        player.playerPosition = moveToPosition;
        return moveReason;
    }

    static void startGameStimulation(Player[] player, int[] diceRange) {
        boolean winnerNotDetermined = true;
        int dieRoll;
        Random rand = new Random();
        int x = rand.nextInt(10);
        int playerCount = player.length;
        int playerToRoll = 0;
        while (winnerNotDetermined) {
            dieRoll = +(int) (Math.random() * ((diceRange[1] - diceRange[0]) + 1)) + diceRange[0];
            System.out.print(player[playerToRoll].name + " rolled a " + dieRoll + " and ");
            String movePlayerPosition = movedToPosition(player[playerToRoll], dieRoll);
            System.out.println(movePlayerPosition);
            if (player[playerToRoll].playerPosition == 100) {
                winnerNotDetermined = false;
                System.out.println(player[playerToRoll].name + " WINS ");
            }
            if (dieRoll != 1 && dieRoll != 6) {
                playerToRoll++;
            }
            if (playerToRoll == 6) {
                playerToRoll = 0;
            }
        }

    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int snakes = Integer.parseInt(in.nextLine());
        for (int snakeN : IntStream.range(0, snakes).toArray()) {
            int[] snakePosition = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Snake snake = new Snake(snakePosition[0], snakePosition[1]);
            board[snakePosition[0] - 1] = new BoardPosition(snake);
        }

        int ladders = Integer.parseInt(in.nextLine());
        for (int ladderN : IntStream.range(0, ladders).toArray()) {
            int[] ladderPosition = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Ladder ladder = new Ladder(ladderPosition[0], ladderPosition[1]);
            board[ladderPosition[0] - 1] = new BoardPosition(ladder);
        }

        int players = Integer.parseInt(in.nextLine());
        Player[] player = new Player[players];
        for (int playerNo : IntStream.range(0, players).toArray()) {
            String playerName = in.nextLine();
            player[playerNo] = new Player(playerName);
        }

        printBoard();

        int[] diceRange = new int[]{1, 6};
        startGameStimulation(player, diceRange);
    }

    private static void printBoard() {
        System.out.println("Print board");
        for (int i = 100; i >= 1; i--) {
            if (board[i] == null) {
                System.out.print(i + 1 + " ");
                board[i] = new BoardPosition();
            } else if (board[i].snake != null) {
                System.out.print("S  ");
            } else if (board[i].ladder != null) {
                System.out.print("L  ");
            } else {
                System.out.print(i + 1 + " ");
            }
            if (i % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

}
