/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.info6205.menace_tictactoe.business_logic;

import java.util.Observable;

/**
 *
 * @author eswar
 */
public class GameStats extends Observable {

    private static GameStats gameStats;
    private Player player2;
    private int wins;
    private int looses;
    private int draws;
    private boolean isTraining;
    private int turns;
    private int[][] board;
    private int iterations;

    {
        player2 = Player.MENACE;
        wins = 0;
        looses = 0;
        draws = 0;
        isTraining = false;
        turns = 0;
        board = new int[3][3];
        iterations = 1;
    }

    // TODO: Compress the logic into a single for loop, increase the space usage
    public WinState gameState() {

        // check column
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
                setWins(wins + 1);
                reset();
                return WinState.PLAYER1;
            }
            if (count_minus1_ == 3) {
                setLooses(looses + 1);
                reset();
                return WinState.PLAYER2;
            }
        }
        //check row
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
                setWins(wins + 1);
                reset();
                return WinState.PLAYER1;
            }
            if (count_minus1_ == 3) {
                setLooses(looses + 1);
                reset();
                return WinState.PLAYER2;
            }
        }

        //check diag
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
            setWins(wins + 1);
            reset();
            return WinState.PLAYER1;
        }
        if (count_minus1_ == 3) {
            setLooses(looses + 1);
            reset();
            return WinState.PLAYER2;
        }

        //check anti diag
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
            setWins(wins + 1);
            reset();
            return WinState.PLAYER1;
        }
        if (count_minus1_ == 3) {
            setLooses(looses + 1);
            reset();
            return WinState.PLAYER2;
        }
//        boolean allNotVisited = false;
//        for(int i = 0;i<3;i++){
//            for(int j = 0;j<3;j++){
//                if(board[i][j]==0){
//                    allNotVisited = true;
//                    break;
//                }
//            }
//        }
        
        //check draw
        if (turns >= 9 ) {
            setDraws(draws + 1);
            reset();
            return WinState.DRAW;
        } else {
            return WinState.INPROGRESS;
        }
    }

    public GameStats() {
        resetBoard();
    }

    // resets the board to initial values
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    //    
    public void reset() {

        resetBoard();
        turns = 0;
//        iterations = 1;

    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        setChanged();
        this.board = board;
        notifyObservers("board");
    }
    
    public void setBoard(int row,int col, int val){
        setChanged();
        board[row][col] = val;
        notifyObservers("board");
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(String playerType) {
        switch (playerType) {
            case "menace":
                player2 = Player.MENACE;
                break;
            case "human":
                player2 = Player.HUMAN;
                break;
            case "perfect":
                player2 = Player.PERFECT;
                break;
            default:
                break;
        }
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        setChanged();
        this.wins = wins;
        notifyObservers("win");
    }

    public int getLooses() {
        return looses;
    }

    public void setLooses(int looses) {
        setChanged();
        this.looses = looses;
        notifyObservers("loss");
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        setChanged();
        this.draws = draws;
        notifyObservers("draw");
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iter) {
        this.iterations = iter;
    }

    public boolean isIsTraining() {
        return isTraining;
    }

    public void setIsTraining(boolean isTraining) {

        this.isTraining = isTraining;

    }

    public static GameStats getObj() {
        if (gameStats == null) {
            gameStats = new GameStats();
        }

        return gameStats;
    }

}
