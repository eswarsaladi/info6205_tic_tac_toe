package edu.neu.info6205.menace_tictactoe.gui;

import edu.neu.info6205.menace_tictactoe.Game;
import static edu.neu.info6205.menace_tictactoe.Game.gameEnded;
import static edu.neu.info6205.menace_tictactoe.Game.gameState;
import static edu.neu.info6205.menace_tictactoe.Game.resetBoard;
import static edu.neu.info6205.menace_tictactoe.Game.turns;
import edu.neu.info6205.menace_tictactoe.Menace1;
import edu.neu.info6205.menace_tictactoe.business_logic.GameStats;
import edu.neu.info6205.menace_tictactoe.business_logic.Menace;
import edu.neu.info6205.menace_tictactoe.business_logic.MenaceData;
import edu.neu.info6205.menace_tictactoe.business_logic.MenaceVsMenace;
import edu.neu.info6205.menace_tictactoe.business_logic.Player;
import edu.neu.info6205.menace_tictactoe.business_logic.WinState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author eswar
 */
public class Board extends JPanel {

    public static Board currBoard;
//    private GameStats gameStats;
//    private Menace m;

    public Board() {
        this.width = 60;
//        gameStats = GameStats.getObj();

        initComponents();

    }

    private void initComponents() {
        this.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();

                buttons[i][j].setPreferredSize(new Dimension(width, width));
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                this.add(buttons[i][j]);

                if (Game.player2 == "human") {

                    buttons[i][j].addActionListener(listener);
                }
            }
        }

    }

//    public void makeMenaceMove() {
//        m = new Menace();
//        int[] loc = m.makeMove(1);
////        this.updateCell(loc[0], loc[1], 1);
//    }

    public void addListeners() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].addActionListener(listener);
            }
        }
    }

    public void reset() {
//        gameStats.reset();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//                set text to empty
                buttons[i][j].setText("");
//                remove all listeners
                
                for (ActionListener al : buttons[i][j].getActionListeners()) {
                    buttons[i][j].removeActionListener(al);
                }
            }
        }
        if(Game.player2=="human"){
//            makeMenaceMove();
//            gameStats.setTurns(gameStats.getTurns()+1);
            addListeners();
        }
    }

    // variables
    private final int width;
    private final JButton[][] buttons = new JButton[3][3];
    private final ActionListener listener = (ActionEvent evt) -> {
        try{
        int row = -1,col=-1;
        for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (evt.getSource() == buttons[i][j]) {
                        if(buttons[i][j].getText()!="") return;
                        row = i;
                        col = j;
                        break;
                        
                    }
                }
        }
        
        Game.humanMakeMove(row, col);
        Thread.sleep(500);
        if (gameState().equals("the game is still being played")) {
                Menace1.menace1Play();
                turns++;
            }
            Thread.sleep(500);
            if (!gameState().equals("the game is still being played")) {
                gameEnded = true;
                resetBoard();
            }}catch(Exception e){
                System.out.println(e);
            }
    };
    


    // modify cell values
    public void updateCell(int row, int col, int player) {

        if ("".equals(buttons[row][col].getText())) {
            if (player == 1) {
                buttons[row][col].setText("O");
            } else if (player == -1) {
                buttons[row][col].setText("X");
            }
        }
    }
    
    // get singleton object
    public static Board getObj() {
        if (currBoard == null) {
            currBoard = new Board();
        }
        return currBoard;
    }

}
