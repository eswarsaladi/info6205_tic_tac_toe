/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.info6205.menace_tictactoe.business_logic;

import edu.neu.info6205.menace_tictactoe.gui.Board;

/**
 *
 * @author eswar
 */
public class MenaceVsMenace extends Thread {

    Menace menace1;
    Menace menace2;
    GameStats gameStats;
    Board board;

    public MenaceVsMenace() {
        menace1 = new Menace();
        menace2 = new Menace();
        gameStats = GameStats.getObj();
        board = Board.getObj();
    }

    // game play happens here
    public void startGame() {
        
        // reset gameStats
        
        for (int run = 0; run < gameStats.getIterations(); run++) {
            gameStats.reset();
            System.out.println(run);
            boolean gameEnded = false;
            while (!gameEnded) {
                // make first player move
                WinState winState = gameStats.gameState();
                if (winState == WinState.INPROGRESS) {

                    if (gameStats.getTurns() % 2 == 0) {
                        int[] index = menace1.makeMove(1);
                        // change the value in the board at index to O
                        board.updateCell(index[0], index[1], 1);

                    } else {
                        int[] index = menace2.makeMove(2);
                        // change the value in the board at index to X
                        board.updateCell(index[0], index[1], 2);
                    }
                    gameStats.setTurns(gameStats.getTurns() + 1);

                }
                else{
                    gameEnded = true;
                }
             
//                else {
//                    if (null != winState) {
//                        switch (winState) {
//                            case PLAYER1 -> {
//                                // log that the player 1 won
//                                int gameState = 4;
//                                int player = 1;
//                                MenaceData.getObj().addFromCurrentToStore(player, gameState);
//                                // reward the menace data
//
//                            }
//                            case PLAYER2 -> {
//                                // log that the player 2 has won
//                                int gameState = 4;
//                                int player = 2;
//                                MenaceData.getObj().addFromCurrentToStore(player, gameState);
//                                // reward the menace data
//                            }
//                            case DRAW -> {
//                                // log that it is draw
//                                int gameState = 1;
//                                int player = 2;
//                                MenaceData.getObj().addFromCurrentToStore(player, gameState);
//                                player = 1;
//                                MenaceData.getObj().addFromCurrentToStore(player, gameState);
//                                // reward both menaces
//                            }
//                            default -> {
//                            }
//                        }
//                        gameEnded = true;
//                    }
//
//                }
            }

        }
    }

    @Override
    public void run() {
        startGame();
    }

}
