import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        tictac game = new tictac();
        AI enemy = new AI();
        game.initializeBoard();
        System.out.println("Tic-Tac-Toe!");
        do
        {
            System.out.println("Current board layout:");
            game.printBoard();
            int row;
            int col;
            do

            {

                System.out.println("Player " + game.getCurrentPlayerMark() + ", enter an empty row and column to place your mark!");
                row = scan.nextInt()-1;
                col = scan.nextInt()-1;
            }
            while (!game.placeMark(row, col));
            game.changePlayer();
            int[] moveAI  = game.convertSpaceToRowCol(enemy.choose(game));
            if(!game.isGameOVer()){
            game.placeMark(moveAI[0], moveAI[1]);
            game.changePlayer();}
        }
        while(!game.checkForWin() && !game.isBoardFull());
        if (game.isBoardFull() && !game.checkForWin())
        {
            System.out.println("The game was a tie!");
        }
        else
        {
            System.out.println("Current board layout:");
            game.printBoard();
            game.changePlayer();
            System.out.println(Character.toUpperCase(game.getCurrentPlayerMark()) + " Wins!");
        }
    }
    }

