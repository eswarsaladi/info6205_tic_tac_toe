/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.neu.info6205.menace_tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author eswar
 */
public class Menace1Test {

    public Menace1Test() {
    }

    @BeforeEach
    public void setUp() {
        Game.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Game.board[i][j] = 0;
            }
        }
        Game.turns = 0;
    }

    /**
     * Test of convertIntToBoardLoc method, of class Menace1.
     */
    @Test
    public void testConvertIntToBoardLoc() {
        System.out.println("convertIntToBoardLoc");
        int board_location = 2;
        int[] expResult = new int[] { 0, 1 };
        int[] result = Menace1.convertIntToBoardLoc(board_location);
        assertArrayEquals(expResult, result);

    }

    @Test
    public void testMenace1Play() {
        
        Game.board[0][0] = 1;
        Game.board[0][1] = -1;
        Game.turns = 2;

        Menace1.menace1Play();

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Game.board[i][j] == 1)
                    count++;
            }
        }
        assertEquals(count, 2);
    }



    /**
     * Test of incrementWins method, of class Menace1.
     */
    @Test
    public void testIncrementWins() {
        System.out.println("incrementWins");
        int prevCount = Menace1.gamesWon;
        Menace1.incrementWins();
        assertEquals(Menace1.gamesWon, prevCount + 1);

    }

    /**
     * Test of incrementLosses method, of class Menace1.
     */
    @Test
    public void testIncrementLosses() {
        System.out.println("incrementLosses");
        int prevCount = Menace1.gamesLost;
        Menace1.incrementLosses();
        assertEquals(Menace1.gamesLost, prevCount + 1);
    }

    /**
     * Test of incrementDraws method, of class Menace1.
     */
    @Test
    public void testIncrementDraws() {
        System.out.println("incrementDraws");
        int prevCount = Menace1.gamesDraw;
        Menace1.incrementDraws();
        assertEquals(Menace1.gamesDraw, prevCount + 1);

    }

}
