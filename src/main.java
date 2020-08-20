import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class main {
    static tictac game = new tictac(); // creates new game
    static AI enemy = new AI(); // minimax algorithm
    static JFrame frame = new JFrame("Tic-tac-toe!");
    static JPanel panel = new JPanel();
    static HashMap<Integer, JButton> buttonMap = new HashMap<>(); // HashMap for buttons for later use
    static boolean WAIT = false;
    static int LOSS = 0; // counts users losses
    static int TIE = 0; // counts users ties
    public static void main(String[] args) {
        frame.setVisible(true);
        panel.setVisible(true);
        panel.setLayout(new java.awt.GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.initializeBoard(); // creates a new [3][3] tictactoe board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final JButton button = new JButton(); //creates a button for every field in the [3][3] grid
                int position = game.convertRowColToSpace(i, j); //converts the position to a single digit integer from [row][col] coordinates
                button.setText("");
                button.setBackground(Color.white);
                button.setName(String.valueOf(position));
                buttonMap.put(position, button);
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(button);
                button.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) { //listens for button clicks
                                buttonClicked(button); }
                        });
            }
        }
        frame.add(panel);
        frame.setSize(400,400);
        frame.setVisible(true);
    }

    public static void buttonClicked(JButton button){ // gets called after a button is clicked
        if(!game.isGameOVer()) { // checks whether the game is over
            boolean next = game.buttonClicked(button); // places mark on button and returns whether it was successful
            WAIT = false; // WAIT boolean tells the "AI" whether it should wait for user input -> used for ties.
            continueCheck(); // checks if "AI" is needed or game is already won/tied
            if (next && !WAIT) { // if user has placed a mark and "AI" isn't told to wait
                game.changePlayer(); // changes current player from 'x' to '0' or vice versa
                int position = enemy.choose(game); // stores the move the AI wants to make in a single digit Integer
                int[] moveAI = game.convertSpaceToRowCol(position); // converting the single digit integer to [row][col] coordinates
                game.placeMark(moveAI[0], moveAI[1]); //places the algorithms move on the board
                buttonMap.get(position).setText(String.valueOf(game.getCurrentPlayerMark())); //updates button
                continueCheck(); //checks if game can continue
                game.changePlayer(); // changes current player from 'x' to '0' or vice versa

            }
            continueCheck();//checks if game can continue
        }
    }

    public static void continueCheck(){
        if(game.isGameTied()){ // checks for tie
            TIE++; // increases TIE count by 1
            WAIT=true; // tells enemy to wait
            GameOver(0); // activates game over function indicating that it was a tie
        }
        if(game.checkForWin()){ //checks for win
            LOSS++; // increases LOSS count by 1
            GameOver(1); // activates game over function indicating that it was a tie
        }
    }

    public static void GameOver(int type){
        JOptionPane.showMessageDialog(null, + type==1 ? (String.valueOf(game.getCurrentPlayerMark()) + " Wins! You have lost " + LOSS + " times so far!" ) : ("The game was a tie! You have tied " + TIE + " times so far!"))  ;
        // displays the result
        game.initializeBoard(); // initializes new board
        for(int i = 0; i < buttonMap.size(); i++){
            buttonMap.get(i).setText(""); // clears buttons on the GUI
        }
    }
}
