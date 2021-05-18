package snakeAndLadder;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

class Snake {
    int head;
    int tail;
    int snakeId;
    static int primaryId;

    Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
        this.snakeId = generateId();
    }

    int generateId() {
        return ++primaryId;
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

class BoardTile {
    Ladder ladder;
    Snake snake;

    BoardTile(Ladder ladder) {
        this.ladder = ladder;
    }

    BoardTile(Snake snake) {
        this.snake = snake;
    }

    public BoardTile() {
        this.ladder = null;
        this.snake = null;
    }
}

class Board {
    BoardTile[] boardTiles;

    Board(int size) {
        boardTiles = new BoardTile[size];
        createBoard(size);
    }

    void createBoard(int size) {
        for (int i = 0; i < size; i++) {
            boardTiles[i] = new BoardTile();
        }
    }

    void printBoard() {
        System.out.println("Print board");
        for (int i = boardTiles.length - 1; i >= 1; i--) {
            if (boardTiles[i].snake != null) {
                System.out.print("S  ");
            } else if (boardTiles[i].ladder != null) {
                System.out.print("L  ");
            } else {
                System.out.print(i + " ");
            }
            if (i % 10 == 1) {
                System.out.println();
            }
        }
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
    static Board board;

    static String movedToPosition(Player player, int diceRolled) {
        int playerCurrentPosition = player.playerPosition;
        String moveReason = "";
        int moveToPosition = playerCurrentPosition + diceRolled;
        moveReason = "moved from " + playerCurrentPosition + " to " + moveToPosition;

        if (moveToPosition > 100) {
            moveToPosition = player.playerPosition;
            moveReason = "rolled dice exceeded 100, so loses the turn";
        } else if (board.boardTiles[moveToPosition].snake != null) {
            moveToPosition = board.boardTiles[moveToPosition].snake.tail;
            moveReason += " but got bitten by the snake and dragged to " + moveToPosition;
        } else if (board.boardTiles[moveToPosition].ladder != null) {
            moveToPosition = board.boardTiles[moveToPosition].ladder.end;
            moveReason += " caught the ladder and risen to " + moveToPosition;
        }

        player.playerPosition = moveToPosition;
        return moveReason;
    }

    static void startGameStimulation(Player[] player, int[] diceRange) {
        boolean winnerNotDetermined = true;
        int dieRoll;
        int playerToRoll = 0;
        int playerCount = player.length;
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
            if (playerToRoll == playerCount) {
                playerToRoll = 0;
            }
        }

    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        board = new Board(101);
        board.printBoard();

        int snakes = Integer.parseInt(in.nextLine());
        for (int snakeN : IntStream.range(0, snakes).toArray()) {
            int[] snakePosition = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Snake snake = new Snake(snakePosition[0], snakePosition[1]);
            board.boardTiles[snakePosition[0] - 1] = new BoardTile(snake);
        }

        int ladders = Integer.parseInt(in.nextLine());
        for (int ladderN : IntStream.range(0, ladders).toArray()) {
            int[] ladderPosition = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Ladder ladder = new Ladder(ladderPosition[0], ladderPosition[1]);
            board.boardTiles[ladderPosition[0] - 1] = new BoardTile(ladder);
        }

        int players = Integer.parseInt(in.nextLine());
        Player[] player = new Player[players];
        for (int playerNo : IntStream.range(0, players).toArray()) {
            String playerName = in.nextLine();
            player[playerNo] = new Player(playerName);
        }

        int[] diceRange = new int[]{1, 6};
        startGameStimulation(player, diceRange);
    }

}
