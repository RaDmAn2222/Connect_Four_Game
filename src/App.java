import java.util.Scanner;

public class App {

    static Scanner inputReader = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        game();

        inputReader.close();
    }

    public static void game() {
        int rowBoard = 6;
        int columnBoard = 7;
        String[][] board = new String[rowBoard][columnBoard];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "- ";
            }
        }

        int userScore = 0;
        int computerScore = 0;

        while (true) {
            showboard(board);

            board = userTaw(board);

            if (isBoardFull(board)) {
                userScore = checkScore(board, "X ");
                computerScore = checkScore(board, "O ");
                endGame(board, userScore, computerScore);
                break;
            }

            board = computerTaw(board);

            if (isBoardFull(board)) {
                userScore = checkScore(board, "X ");
                computerScore = checkScore(board, "O ");
                endGame(board, userScore, computerScore);
                break;
            }
        }
    }

    public static boolean isBoardFull(String[][] board) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j].equals("- ")) {
                return false;
            }
        }
        return true;
    }

    public static void endGame(String[][] board, int userScore, int computerScore) {
        showboard(board);
        System.out.println("The game is over!");
        System.out.println("User Score: " + userScore);
        System.out.println("Computer Score: " + computerScore);
        if (userScore > computerScore) {
            System.out.println("You won!!");
        } else if (userScore == computerScore) {
            System.out.println("The game is tied!");
        } else {
            System.out.println("Computer won :(");
        }
    }

    public static int checkScore(String[][] board, String playerSymbol) {
        int score = 0;
        int count;

        // Horizontal check
        for (int i = 0; i < board.length; i++) {
            count = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(playerSymbol)) {
                    count++;
                    if (count == 4) {
                        score++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Vertical check
        for (int j = 0; j < board[0].length; j++) {
            count = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j].equals(playerSymbol)) {
                    count++;
                    if (count == 4) {
                        score++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Diagonal (top-left to bottom-right)
        for (int startRow = 0; startRow < board.length; startRow++) {
            count = 0;
            for (int i = startRow, j = 0; i < board.length && j < board[0].length; i++, j++) {
                if (board[i][j].equals(playerSymbol)) {
                    count++;
                    if (count == 4) {
                        score++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
        }

        for (int startColumn = 1; startColumn < board[0].length; startColumn++) {
            count = 0;
            for (int i = 0, j = startColumn; i < board.length && j < board[0].length; i++, j++) {
                if (board[i][j].equals(playerSymbol)) {
                    count++;
                    if (count == 4) {
                        score++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Anti-Diagonal (top-right to bottom-left)
        for (int startRow = 0; startRow < board.length; startRow++) {
            count = 0;
            for (int i = startRow, j = board[0].length - 1; i < board.length && j >= 0; i++, j--) {
                if (board[i][j].equals(playerSymbol)) {
                    count++;
                    if (count == 4) {
                        score++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
        }

        for (int startColumn = board[0].length - 2; startColumn >= 0; startColumn--) {
            count = 0;
            for (int i = 0, j = startColumn; i < board.length && j >= 0; i++, j--) {
                if (board[i][j].equals(playerSymbol)) {
                    count++;
                    if (count == 4) {
                        score++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
        }

        return score;
    }

    public static String[][] computerTaw(String[][] board) {
        int randomColumn = (int) Math.floor(Math.random() * 7);
        while (!board[0][randomColumn].equals("- ")) {
            randomColumn = (int) Math.floor(Math.random() * 7);
        }

        if (board[0][randomColumn].equals("- ")) {
            for (int i = board.length - 1; i >= 0; i--) {
                if (board[i][randomColumn].equals("- ")) {
                    board[i][randomColumn] = "O ";
                    break;
                }
            }
        }

        return board;
    }

    public static String[][] userTaw(String[][] board) {
        int column = getInputNum("Please select a column between 1 to 7:") - 1;
        while (!board[0][column].equals("- ")) {
            System.out.println("This cell is filled!");
            column = getInputNum("Please select a column between 1 to 7:") - 1;
        }

        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column].equals("- ")) {
                board[i][column] = "X ";
                break;
            }
        }

        return board;
    }

    public static void showboard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int getInputNum(String statement) {
        System.out.println(statement);
        int input = inputReader.nextInt();
        inputReader.nextLine();
        while (input > 7 || input < 1) {
            System.out.println("Be carefull! " + statement);
            input = inputReader.nextInt();
            inputReader.nextLine();
        }
        return input;
    }
}
