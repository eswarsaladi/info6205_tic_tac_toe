package edu.neu.info6205.menace_tictactoe;

import edu.neu.info6205.menace_tictactoe.gui.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eswar
 */
public class Driver {

    public static void main(String[] args) {
        // Logger logger = LoggerFactory.getLogger(Driver.class);
        // logger.info("Hello World");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu();

            }
        });
    }
}
