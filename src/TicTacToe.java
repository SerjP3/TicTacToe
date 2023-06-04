import java.util.Scanner;

public class TicTacToe {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("\nLet's play tic tac toe");

        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        printBoard(board);

        int row = 0;
        int column = 0;

        for(int i = 0; i < 9; i++){

            if(i % 2 == 0){
                System.out.println("Turn: X ");
                int[] xChoice = askUser(board);
                row = xChoice[0];
                column = xChoice[1];
                board[row][column] = 'X';
            } else {
                System.out.println("Turn: O ");
                int[] oChoice = askUser(board);
                row = oChoice[0];
                column = oChoice[1];
                board[row][column] = 'O';
            }
            printBoard(board);

            int count = checkWin(board);
            if(count == 3){
                System.out.print("X wins!");
                break;
            }else if(count == -3){
                System.out.print("O wins!");
                break;
            }else if(i == 8){
                System.out.println("It's a tie!");
            }
        }

        scan.close();
    }


    /**
     * Function name - printBoard()
     * @param board (char[][])
     */

    public static void printBoard(char[][] board){
        for(int i = 0; i < board.length; i++){
            System.out.print("\n\t\t");
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Function name – askUser
     * @param board (char[][] board)
     * @return spot (int[])
     */

    public static int[] askUser(char[][] board){
        System.out.print("Pick a row and column number: ");
        int row = scan.nextInt() - 1;
        int column = scan.nextInt() - 1;
        int[] choice = new int[2];
        boolean isInputCorrect = (row < 0 && row > 2) || (column < 0 && column > 2);

        while(isInputCorrect){
            System.out.print("Out of bonds. Pick a row and a column: ");
            row = scan.nextInt();
            column = scan.nextInt();
        }
        while(!(board[row][column] == '_')){
            System.out.print("Spot taken. Pick a row and a column: ");
            row = scan.nextInt() - 1;
            column = scan.nextInt() - 1;
        }

        choice[0] = row;
        choice[1] = column;
        return choice;
    }

    /**
     * Function name - checkWin
     * @param board char[][]
     * @return count int
     *
     */
    public static int checkWin(char[][] board){

        int count = 0;
        int rows = checkRows(board);
        if(Math.abs(rows) == 3)
            return rows;

        int columns = checkColumns(board);
        if(Math.abs(columns) == 3)
            return columns;

        int leftDiagonal = checkLeft(board);
        if(Math.abs(leftDiagonal) == 3)
            return leftDiagonal;

        int rightDiagonal = checkRight(board);
        if(Math.abs(rightDiagonal) == 3)
            return rightDiagonal;
        return count;
    }

    public static int checkRows(char[][] board){

        int count = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 'X')
                    count++;
                else if(board[i][j] == 'O')
                    count--;
            }
            if(Math.abs(count) == 3)
                return count;
            else count = 0;
        }

        return count;
    }

    public static int checkColumns(char[][] board){

        int count = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[j][i] == 'X')
                    count++;
                else if(board[j][i] == 'O')
                    count--;
            }
            if(Math.abs(count) == 3)
                return count;
            else count = 0;
        }
        return count;
    }

    public static int checkLeft(char[][] board){

        int count = 0;
        for(int i = 0; i < board.length; i++){
            if(board[i][i] == 'X')
                count++;
            else if(board[i][i] == 'O')
                count--;
        }

        return count;
    }

    public static int checkRight(char[][] board){

        int count = 0;
        for(int i = board.length - 1; i >= 0; i--){
            if(board[i][Math.abs(i-2)] == 'X')
                count++;
            else if(board[i][Math.abs(i-2)] == 'O')
                count--;
        }
        return count;
    }


}
