import java.util.Scanner;

/** outputs: displays board and asks for input of each player
 * inputs: moves of player X and player O
 *
 * Start
 *
 * output *TTT board outline*
 * output "row of currentPlayer:"
 * input row for X
 * output "column of X:"
 * input column for X
 * output *filled in board*
 * output "row of O:"
 * input row for O
 * output "column of O:"
 * input column for O
 *
 * output "Player _ wins!" or "its a tie!"
 *
 * output "Would you like to play again?"
 * input 'Y' or 'N'
 *
 * End
 */
public class Main {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            String currentPlayer = "X";
            boolean gameWon = false;
            boolean gameTie = false;

            while (!gameWon && !gameTie) {
                display();
                int rowMove = SafeInput.getRangedInt(console, "Enter row for " + currentPlayer, 1, 3) - 1;
                int colMove = SafeInput.getRangedInt(console, "Enter column for " + currentPlayer, 1, 3) - 1;

                if (isValidMove(rowMove, colMove)) {
                    board[rowMove][colMove] = currentPlayer;
                    gameWon = isWin(currentPlayer);
                    gameTie = isTie();

                    if (gameWon) {
                        display();
                        System.out.println("Player " + currentPlayer + " wins!");
                    } else if (gameTie) {
                        display();
                        System.out.println("It's a tie!");
                    } else {
                        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                    }
                } else {
                    System.out.println("Invalid move, try again.");
                }
            }

            playAgain = SafeInput.getYNConfirm(console, "Do you want to play again?");
        } while (playAgain);

        console.close();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("  1 2 3");
        for (int i = 0; i < ROW; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) System.out.print("|");
            }
            System.out.println();
            if (i < ROW - 1) System.out.println("  -----");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
