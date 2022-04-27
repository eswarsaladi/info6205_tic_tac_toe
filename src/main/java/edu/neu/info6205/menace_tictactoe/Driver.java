package edu.neu.info6205.menace_tictactoe;


import edu.neu.info6205.menace_tictactoe.gui.Menu;

/**
 *
 * @author eswar
 */
public class Driver {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu();

            }
        });
    }
}
