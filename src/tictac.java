import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class tictac {
    private char currentPlayerMark;
    private char[][] board;

    public tictac(){
        board = new char[3][3];
        currentPlayerMark = 'x';
        initializeBoard();
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayerMark()
    {
        return currentPlayerMark;
    } // returns either x or 0, depending on the current player


    public void initializeBoard() { // creates the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
    public void printBoard() {
        System.out.println("-------------"); //fancy

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    public boolean checkForWin(){
        return(checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    public boolean isGameOVer() { return(checkForWin() || isBoardFull());}

    public boolean isGameTied() { return !checkForWin() && isBoardFull(); }

    public boolean isBoardFull() {
        for(int i=0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == '-'){
                    return false;
                }
            }
        }
        return true;
    }

    public void changePlayer(){
        if(currentPlayerMark == 'x'){
            currentPlayerMark = '0';
        }
        else{
            currentPlayerMark = 'x';
        }
    }

    public boolean placeMark(int row, int col){
        if(row>=0 && row < 3 && col >= 0 && col < 3){
            if(board[row][col] == '-'){
                board[row][col] = currentPlayerMark;
                return true;
            }
        }
        return false;
    }

    public void resetSpace(int row, int col){
        board[row][col] = '-';
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if(checkRowCol(board[i][0], board[i][1], board[i] [2])){
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin(){
        for (int i = 0; i < 3; i++){
            if(checkRowCol(board[0][i], board[1][i], board[2][i])){
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin(){
        return((checkRowCol(board[0][0], board[1][1], board[2][2] ) || (checkRowCol(board[0][2], board[1][1], board[2][0]))));
    }



    private boolean checkRowCol(char a, char b, char c){
        return(( a != '-') && (b==a) && (c==b) );
    }

    public HashSet<Integer> getEmptyFields(){ // returns empty fields on the board
        HashSet<Integer> emptyFields = new HashSet<>();
        for(int i=0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == '-'){
                    emptyFields.add(convertRowColToSpace(i, j));
                }
            }
        }
        return emptyFields;
    }

    public int convertRowColToSpace(int row, int col){ //converts the coordinates on the board to a single digit number
        int position = 0;
        for(int i=0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == row && j == col){
                    return position;
                }
                position++;
            }
        }
        return -1;
    }

    public int[] convertSpaceToRowCol(int space){ //converts single digit position to coordinates
        int position = 0;
        for(int i=0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(position == space){
                    int[] rowcol = new int[2];
                    rowcol[0] = i;
                    rowcol[1] = j;
                    return rowcol;
                }
                position++;
            }
        }
        int[] empty = new int[0];
        return empty;
    }


}
