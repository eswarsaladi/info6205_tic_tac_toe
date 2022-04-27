/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.info6205.menace_tictactoe.business_logic;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author eswar
 */
public class Menace {  
    
    public int[] makeMove(int player) {
        
        MenaceData menace=MenaceData.getObj();
        GameStats gameStats = GameStats.getObj();
        int[][] board = gameStats.getBoard();
        String boardState = Arrays.deepToString(board);
        Random rand=new Random();
        menace.ifDataIsAbsent(boardState, player);
        int choose_one=rand.nextInt(menace.sizeOfArray(boardState, player));
        while(true){
            int[] temp=new int[2];
            Integer board_location=menace.getValOfElement(boardState, player,choose_one);
            
            temp[0] = (board_location-1)/3;
            temp[1] = (board_location-1)%3;
                 
            if(board[temp[0]][temp[1]]==0){
                if(player==1){
                    try {
                        board[temp[0]][temp[1]]=1;
                        gameStats.setBoard(board);
                        menace.addToCurrentGame(1, boardState, board_location);
                        if(gameStats.isIsTraining()) Thread.sleep(0);
                        else Thread.sleep(2000);
                        return temp;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menace.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(player==2){
                    try {
                        board[temp[0]][temp[1]]=-1;
                        gameStats.setBoard(board);
                        menace.addToCurrentGame(2, boardState, board_location);
                        if(gameStats.isIsTraining()) Thread.sleep(0);
                        else Thread.sleep(2000);
                        return temp;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menace.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else{
                choose_one=rand.nextInt(menace.sizeOfArray(boardState, player));
            }
        }
        
    }
}
