import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class main {
    static tictac game = new tictac();
    static AI enemy = new AI();
    static JFrame frame = new JFrame("Tic-tac-toe!");
    static JPanel panel = new JPanel();
    static HashMap<Integer, JButton> buttonMap = new HashMap<>();
    static boolean WAIT = false;
    static int LOSS = 0;
    static int TIE = 0;
    public static void main(String[] args) {
        frame.setVisible(true);
        panel.setVisible(true);
        panel.setLayout(new java.awt.GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.initializeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final JButton button = new JButton();
                int position = game.convertRowColToSpace(i, j);
                button.setText("");
                button.setBackground(Color.white);
                button.setName(String.valueOf(position));
                buttonMap.put(position, button);
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(button);
                button.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                buttonClicked(button); }
                        });
            }
        }
        frame.add(panel);
        frame.setSize(400,400);
        frame.setVisible(true);
    }

    public static void buttonClicked(JButton button){
        if(!game.isGameOVer()) {
            boolean next = game.buttonClicked(button);
            WAIT = false;
            continueCheck();
            if (next && !WAIT) {
                game.changePlayer();
                int position = enemy.choose(game);
                int[] moveAI = game.convertSpaceToRowCol(position);
                if (!game.isGameOVer()) {
                    game.placeMark(moveAI[0], moveAI[1]);
                    buttonMap.get(position).setText(String.valueOf(game.getCurrentPlayerMark()));
                     continueCheck();
                    game.changePlayer();
                }
            }
            continueCheck();
        }
        continueCheck();
    }

    public static void continueCheck(){
        if(game.isGameTied()){
            TIE++;
            JOptionPane.showMessageDialog(null,"The game was a tie! You have tied " + TIE + " times so far!");
            WAIT=true;
            GameOver(0);
        }
        if(game.checkForWin()){
            LOSS++;
            String currentPlayer = String.valueOf(game.getCurrentPlayerMark());
            JOptionPane.showMessageDialog(null,currentPlayer +  " Wins! You have lost " + LOSS + " times so far!");
            GameOver(1);
        }
    }

    public static void GameOver(int type){
        game.initializeBoard();
        for(int i = 0; i < buttonMap.size(); i++){
            buttonMap.get(i).setText("");
        }
    }
}
