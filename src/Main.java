import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean quit = true;
        String[][] board = {
                {" - ", " - ", " - "},
                {" - ", " - ", " - "},
                {" - ", " - ", " - "}}; // Initialize the 2D Array (Board)
        do{
            System.out.println("Player 1:");
            playerMove(scan, board, 1);
            System.out.println(""); // Buffer
            displayBoard(board);
            System.out.println(""); // Buffer
            if(isWin(board,1) || isWin(board,2) || isTie(board)){
                if (!roundEnd(scan, board))
                    quit = false;
            }
            System.out.println("Player 2:");
            playerMove(scan, board, 2);
            System.out.println(""); // Buffer
            displayBoard(board);
            System.out.println(""); // Buffer
            if(isWin(board,1) || isWin(board,2) || isTie(board)){
                if (!roundEnd(scan, board))
                    quit = false;
            }
        } while (quit);

    }
    private static boolean roundEnd(Scanner scan, String[][] board){
        clearBoard(board);
        if (!InputHelper.getYNConfirm(scan, "PLay Again? [Y/N]: "))
            return false;
        return true;
    }
    private static void playerMove(Scanner scan, String[][] board, int player){
        String move;
        boolean blank = true;
        int row;
        int col;
        move = assign(player);
        do{
            row = InputHelper.getRangedInt(scan, "Enter your move row [1 - 3]", 1, 3) - 1;
            col = InputHelper.getRangedInt(scan, "Enter your move col [1 - 3]", 1, 3) - 1;
            if (isValidMove(board, row, col)){
                board[row][col] = move;
                blank = false;
            } else System.out.println("Invalid Move. Please Try Again\n");
        }while(blank);
    }
    private static String assign(int player){ // Assigns X if Player 1, O if Player 2
        if (player == 1)
            return (" X ");
        else return (" O ");
    }
    private static void clearBoard(String[][] board) {
        //Accesses each row
        for (int r = 0; r < board.length; r++) {
            //Accesses each column in the row
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = " - ";
            }

        }
    }
    private static void displayBoard(String[][] board) {
        //Accesses each row
        for(int r = 0; r < board.length; r++){
            //Accesses each column in the row
               for(int c = 0; c < board[0].length; c++){
                System.out.print(board[r][c]);
            }
               System.out.println(""); //Buffer
        }
    }


    private static boolean isValidMove(String[][] board, int row, int col){
        if(board[row][col].equals(" - "))
            return true;
        else return false;
    }

    private static boolean isWin(String[][] board, int player){
        if(isColWin(board, player) || isRowWin(board, player) || isDiagonalWin(board, player)){
            System.out.println("Player " + player + " Wins!");
            return true;
        }
        return false;
    }

    private static boolean isColWin(String[][] board, int player){
        String move = assign(player);
        if(board[0][0].equals(move) && board[1][0].equals(move) && board[2][0].equals(move))
            return true;
        if(board[0][1].equals(move) && board[1][1].equals(move) && board[2][2].equals(move))
            return true;
        if(board[0][2].equals(move) && board[1][2].equals(move) && board[2][2].equals(move))
            return true;
        return false;
    }

    private static boolean isRowWin(String[][] board, int player){
        String move = assign(player);
        if(board[0][0].equals(move) && board[0][1].equals(move) && board[0][2].equals(move))
            return true;
        if(board[1][0].equals(move) && board[1][1].equals(move) && board[1][2].equals(move))
            return true;
        if(board[2][0].equals(move) && board[2][1].equals(move) && board[2][2].equals(move))
            return true;
        return false;
    }

    private static boolean isDiagonalWin(String[][] board, int player){
        String move = assign(player);
        if(board[0][0].equals(move) && board[1][1].equals(move) && board[2][2].equals(move))
            return true;
        if(board[2][0].equals(move) && board[1][1].equals(move) && board[0][2].equals(move))
            return true;
        return false;
    }

    private static boolean isTie(String[][] board){
        if(boardFull(board) && !isWin(board,1) && !isWin(board,2)){
            System.out.println("Tie Game!");
            return true;
        }
        return false;
    }

    private static boolean boardFull(String[][] board){
        //Accesses each row
        for (int r = 0; r < board.length; r++) {
            //Accesses each column in the row
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].equals(" - "))
                    return false;
            }
        }
        return true;
    }
}