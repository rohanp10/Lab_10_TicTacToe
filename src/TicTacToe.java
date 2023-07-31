import java.util.Scanner;

public class TicTacToe {

    // TicTacToe Game

    // Game PseudoCode

    // Initialize the game, clear the board, move count to 0 and set the player to X (X Always moves first)
    // Display the board, get the coordinates for the move, which should be 1-3 for the row and col
    // Convert the player move to the array indices (which are 0-2)
    // Iterate through the process of obtaining player coordinates until there is a valid move
    // Record the correct move on the board and increment the move counter
    // If it is the correct situation, check for a win or a tie in the game
    // If there is a win or a tie, announce it and prompt the players to play again or exit
    // Change the player for the next move, so that the game can continue

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean continueGame = true;

        do {

            String player = "X";
            int moves = 0;
            boolean gameOver = false;


            clearBoard();

            display();

            do {

                boolean validMove = false;

                int rowMove;
                int colMove;

                do {
                    System.out.print("\n");
                    rowMove = SafeInput.getRangedInt(in, "Player " + player + ": Choose a row", 1, 3) - 1;
                    colMove = SafeInput.getRangedInt(in, "Player " + player + ": Choose a column", 1, 3) - 1;

                    if (isValidMove(rowMove, colMove)) {
                        validMove = true;
                    } else {
                        System.out.println("\nInvalid Move! Choose a space that is currently empty!");
                    }

                } while (!validMove);


                board[rowMove][colMove] = player;
                moves += 1;
                display();

                if (moves >= 5) {
                    if (isWin(player)) {
                        System.out.println("\nPlayer " + player + " won the game! Congratulations!");
                        gameOver = true;
                    } else if (isTie(player, moves)) {
                        System.out.println("\nIt's a tie between Player X and Player O");
                        gameOver = true;
                    }
                }

                if (player.equals("X")) {
                    player = "O";
                } else if (player.equals("O")) {
                    player = "X";
                }

            } while (!gameOver);

        continueGame = SafeInput.getYNConfirm(in, "Do you want to play again");

        } while (continueGame == true);

    }

    private static void clearBoard()
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = "";
            }
        }
    }

    private static void display()
    {

        System.out.print("\n");

        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                if (col == 0){
                    System.out.printf("| %2s | ", board[row][col]);
                }
                else {
                    System.out.printf("%2s | ", board[row][col]);
                }
            }
            System.out.println("");
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals("");
    }

    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player)
    {
        for (int col = 0; col < ROW; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean isTie(String player, int moves) {

        if ((moves >= 9) && (!isWin(player))) {
            return true;
        }
        else {

            boolean winPossible = false;

            for (int row = 0; row < ROW; row++) {
                if (board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X")) {
                    if (!(board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O"))) {
                        winPossible = true;
                    }
                }
            }

            for (int col = 0; col < ROW; col++) {
                if (board[0][col].equals("X") && board[1][col].equals("X") && board[2][col].equals("X")) {
                    if (!(board[0][col].equals("O") || board[1][col].equals("O") || board[2][col].equals("O"))) {
                        winPossible = true;
                    }
                }
            }

            if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) {
                if (!(board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O"))) {
                    winPossible = true;
                }
            }
            if (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) {
                if (!(board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O"))) {
                    winPossible = true;
                }
            }

            if (winPossible == true) {
                return false;
            } else {
                return true;
            }

        }
    }


}