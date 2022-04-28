/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.neu.info6205.menace_tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author eswar
 */
public class GameTest {
    
    public GameTest() {
    }
    
    
   
    
    @BeforeEach
    public void setUp() {
    Game.board = new int[3][3];
    for(int i =0;i<3;i++){
        for(int j =0;j<3;j++){
            Game.board[i][j] = 0;
        }
    }
    Game.turns = 0;
    Game.gameEnded = false;
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void testGameStateWin1Row() {
        System.out.println("gameState");
        String expResult = "1 has won";
        
        Game.board[0][0] = 1;
        Game.board[0][1] = 1;
        Game.board[0][2] = 1;
        String result = Game.gameState();
        assertEquals(expResult, result);
        
        
    }
    
    @Test
    public void testGameStateWin1Col() {
        System.out.println("gameState");
        String expResult = "1 has won";
        
        Game.board[0][1] = 1;
        Game.board[1][1] = 1;
        Game.board[2][1] = 1;
        String result = Game.gameState();
        assertEquals(expResult, result);
        
        
    }
    
    @Test
    public void testGameStateWin1Dig() {
        System.out.println("gameState");
        String expResult = "1 has won";
        
        Game.board[0][0] = 1;
        Game.board[1][1] = 1;
        Game.board[2][2] = 1;
        String result = Game.gameState();
        assertEquals(expResult, result);
        
        
    }
    
    @Test
    public void testGameStateWin1AntiDig() {
        System.out.println("gameState");
        String expResult = "1 has won";
        
        Game.board[0][2] = 1;
        Game.board[1][1] = 1;
        Game.board[2][0] = 1;
        String result = Game.gameState();
        assertEquals(expResult, result);
        
        
    }



    /**
     * Test of resetBoard method, of class Game.
     */
    @Test
    public void testResetBoard() {
        System.out.println("resetBoard");
        Game.resetBoard();
        int[][] board = new int[3][3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = 0;
            }
        }
        Assertions.assertArrayEquals(board, Game.board);
        assertEquals(Game.turns,0);
       
    }

    /**
     * Test of changeBoard method, of class Game.
     */
    @Test
    public void testChangeBoard() {
        System.out.println("changeBoard");
        int row = 1;
        int col = 1;
        int val = 1;
        Game.changeBoard(row, col, val);
        assertEquals(Game.board[row][col],val);
        
    }

    
    
}
