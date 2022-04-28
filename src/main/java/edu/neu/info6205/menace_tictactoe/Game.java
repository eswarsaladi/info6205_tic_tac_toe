package edu.neu.info6205.menace_tictactoe;

import edu.neu.info6205.menace_tictactoe.gui.Board;
import edu.neu.info6205.menace_tictactoe.gui.GameBoard;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game extends Thread {

    public static Logger logger = LoggerFactory.getLogger(Game.class);
    public static int[][] board;
    public static int turns;
    public static int iterationsForMenaceVsMenace = 200000;
    public static int iterationsForMenaceVsOptimal = 10000;
    public static boolean gameEnded;
    public static String player2;

    // state of the game either winnine, loosing, or draw or in progress
    public static String gameState() {
        // check col
        for (int i = 0; i < 3; i++) {
            int count_1_ = 0;
            int count_minus1_ = 0;
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == 1) {
                    count_1_++;
                }
                if (board[j][i] == -1) {
                    count_minus1_++;
                }
            }
            if (count_1_ == 3) {

                return "1 has won";
            }
            if (count_minus1_ == 3) {

                return "-1 has won";
            }
        }
        // check row
        for (int i = 0; i < 3; i++) {
            int count_1_ = 0;
            int count_minus1_ = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 1) {
                    count_1_++;
                }
                if (board[i][j] == -1) {
                    count_minus1_++;
                }

            }
            if (count_1_ == 3) {

                return "1 has won";
            }
            if (count_minus1_ == 3) {

                return "-1 has won";
            }
        }

        // check diag
        int count_1_ = 0;
        int count_minus1_ = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    if (board[i][j] == 1) {
                        count_1_++;
                    }
                    if (board[i][j] == -1) {
                        count_minus1_++;
                    }
                }
            }
        }
        if (count_1_ == 3) {

            return "1 has won";
        }
        if (count_minus1_ == 3) {

            return "-1 has won";
        }

        // check anti diag
        count_1_ = 0;
        count_minus1_ = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i + j == 2) {
                    if (board[i][j] == 1) {
                        count_1_++;
                    }
                    if (board[i][j] == -1) {
                        count_minus1_++;
                    }
                }
            }
        }
        if (count_1_ == 3) {

            return "1 has won";
        }
        if (count_minus1_ == 3) {

            return "-1 has won";
        }

        // check draw
        if (turns == 9) {

            return "its a draw";
        } else {
            return "the game is still being played";
        }
    }

    public static void playMenaceVsMenace() {
        
        Board gameBoard = Board.getObj();
        Menace1.gamesDraw = 0;
        Menace1.gamesWon = 0;
        Menace1.gamesLost = 0;

        for (int i = 0; i < iterationsForMenaceVsMenace; i++) {
            gameBoard.reset();
            turns = 0;
            gameEnded = false;
            
            board = new int[3][3];
            while (!gameEnded) {
                
                if (gameState().equals("the game is still being played")) {
                    if (turns % 2 == 0) {
                        logger.info("Menace1 moves");
                        // System.out.println("Menace1 moves");
                        if (i >= iterationsForMenaceVsMenace - 1) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                logger.error(ex.toString());

                            }
                        }
                        Menace1.menace1Play();
                        // if (i >= iterationsForMenaceVsMenace - 1)
                        // logger.info(Arrays.deepToString(board));
                        System.out.println(Arrays.deepToString(board));

                    } else if (turns % 2 == 1) {
                        logger.info("Menace2 moves");
                        
                        if (i >= iterationsForMenaceVsMenace - 1) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                // Logger.getLogger(Menace1.class.getName()).log(Level.SEVERE, null, ex);
                                logger.error(ex.toString());
                            }
                        }
                        Menace2.menace2Play();
                        logger.info(Arrays.deepToString(board));
                        // System.out.println(Arrays.deepToString(board));
                    }
                } else {
                    logger.info(Arrays.deepToString(board));
                    logger.info(gameState());

                    if (gameState().equals("1 has won")) {
                        Menace1.incrementWins();
                    } else if (gameState().equals("-1 has won")) {
                        Menace1.incrementLosses();
                    } else if (gameState().equals("its a draw")) {
                        Menace1.incrementDraws();
                    }
                    gameEnded = true;
                }
                if (!gameEnded) {
                    turns++;
                }
            }

            Menace1.putDataFromCurrentToMatchBoxes();
            Menace2.putDataFromCurrentToMatchBoxes();
            turns = 0;

        }

    }

    public static void playMenaceVsOptimal() {
        Board gameBoard = Board.getObj();
        
        Random rand = new Random();
        Menace1.gamesDraw = 0;
        Menace1.gamesWon = 0;
        Menace1.gamesLost = 0;
        for (int i = 0; i < iterationsForMenaceVsOptimal; i++) {
            gameBoard.reset();
            turns = 0;
            gameEnded = false;

            int value;
            int[] move = new int[2];

            // System.out.println("iteration number: " + i);
            board = new int[3][3];
            // System.out.println(Arrays.deepToString(board));
            while (!gameEnded) {
                if (gameState().equals("the game is still being played")) {
                    if (turns % 2 == 0) {
                        logger.info("Menace1 moves");
                        // System.out.println("Menace1 moves");
                        if (i >= iterationsForMenaceVsOptimal - 1) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                logger.error(ex.toString());
                                // Logger.getLogger(Menace1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        Menace1.menace1Play();
                        logger.info(Arrays.deepToString(board));
                        // System.out.println(Arrays.deepToString(board));
                    } else if (turns % 2 == 1) {
                        logger.info("Optimal Player plays");
                        // System.out.println("optimal player plays");
                        value = Optimal.TicTacMove(board, -1, move);

                        if (i >= iterationsForMenaceVsOptimal - 1) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                logger.error(ex.toString());
                                // Logger.getLogger(Menace1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        int tempVal = rand.nextInt(10);
                        if (tempVal != 9) {
                            changeBoard(move[0], move[1], -1);
                        } else {
                            int row = rand.nextInt(3);
                            int col = rand.nextInt(3);
                            while (board[row][col] != 0) {
                                row = rand.nextInt(3);
                                col = rand.nextInt(3);
                            }
                            changeBoard(row, col, -1);
                        }
                        logger.info(Arrays.deepToString(board));
                        // System.out.println(Arrays.deepToString(board));
                    }
                } else {
                    logger.info(Arrays.deepToString(board));
                    logger.info(gameState());
                    // System.out.println(gameState());
                    gameEnded = true;
                    if (gameState().equals("1 has won")) {
                        Menace1.incrementWins();
                    } else if (gameState().equals("-1 has won")) {
                        Menace1.incrementLosses();
                    } else if (gameState().equals("its a draw")) {
                        Menace1.incrementDraws();
                    }
                }
                if (!gameEnded) {
                    turns++;
                }
            }

            Menace1.putDataFromCurrentToMatchBoxesO();

        }

    }

    public static void playMenaceVsHuman() {

        Board gameBoard = Board.getObj();
        gameBoard.reset();
        board = new int[3][3];
        gameEnded = false;
        turns = 0;
        Menace1.menace1Play();
        turns++;
    }

    public static void humanMakeMove(int row, int col) {
        // to avoid unintended moves
        if (turns % 2 == 1 && !gameEnded) {
            if (gameState().equals("the game is still being played")) {
                if (board[row][col] == 0) {
                    changeBoard(row, col, -1);
                    turns++;
                }
            }

        }

    }

    public static void resetBoard() {
        player2 = "";
        board = new int[3][3];
        Board.getObj().reset();
    }

    public static void changeBoard(int row, int col, int val) {
        Board gameBoard = Board.getObj();

        board[row][col] = val;
        gameBoard.updateCell(row, col, val);
        // call function to modify the game board;
    }

    @Override
    public void run() {
        if (Game.player2 == "human") {
            // if(Thread.currentThread().isAlive()){
            // Thread.currentThread().stop();
            // }
            Game.playMenaceVsHuman();

        } else if (Game.player2 == "optimal") {
            // if(Thread.currentThread().isAlive()){
            // Thread.currentThread().stop();
            // }
            Game.playMenaceVsOptimal();
        } else if (Game.player2 == "menace") {
            // if(Thread.currentThread().isAlive()){
            // Thread.currentThread().stop();
            // }
            Game.playMenaceVsMenace();
        }

    }
}
