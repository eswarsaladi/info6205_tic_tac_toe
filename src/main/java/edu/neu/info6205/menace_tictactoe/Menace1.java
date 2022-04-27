package edu.neu.info6205.menace_tictactoe;

import edu.neu.info6205.menace_tictactoe.gui.GameBoard;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menace1 {
    public static Map<String, ArrayList<Integer>> matchBoxes = new HashMap<>();
    public static Map<String, Integer> currentGameMoves = new HashMap<>();
    public static int gamesWon = 0;
    public static int gamesLost = 0;
    public static int gamesDraw = 0;
    public static int winRewards=5;
    public static int winRewardsO=15;
    public static int drawRewards=2;
    public static int drawRewardsO=5;
    public static int punishment=-1;
    public static int punishmentO=-1;

    public static void menace1Play() {
        // create a key for inserting into matchboxes
        String boardState = Arrays.deepToString(Game.board);
        Random rand = new Random();
        matchBoxes.putIfAbsent(boardState, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        int choose_one = rand.nextInt(matchBoxes.get(boardState).size());
        while (true) {
            Integer board_location = matchBoxes.get(boardState).get(choose_one);
            int[] temp = convertIntToBoardLoc(board_location);
            if (Game.board[temp[0]][temp[1]] == 0) {
                Game.changeBoard(temp[0], temp[1], 1);
                currentGameMoves.put(boardState, board_location);
              
                return;
            } else {
                choose_one = rand.nextInt(matchBoxes.get(boardState).size());
            }
        }

    }

    public static int[] convertIntToBoardLoc(int board_location) {
        int[] temp = new int[2];
        switch (board_location) {
            case 1:
                temp[0]=0;
                temp[1]=0;
                break;
            case 2:
                temp[0]=0;
                temp[1]=1;
                break;
            case 3:
                temp[0]=0;
                temp[1]=2;
                break;
            case 4:
                temp[0]=1;
                temp[1]=0;
                break;
            case 5:
                temp[0]=1;
                temp[1]=1;
                break;
            case 6:
                temp[0]=1;
                temp[1]=2;
                break;
            case 7:
                temp[0]=2;
                temp[1]=0;
                break;
            case 8:
                temp[0]=2;
                temp[1]=1;
                break;
            case 9:
                temp[0]=2;
                temp[1]=2;
                break;
            default:
                break;
        }

        return temp;
    }

    public static void putDataFromCurrentToMatchBoxes() {
        if (Game.gameState().equals("1 has won")) {
            for (Map.Entry<String, Integer> e : currentGameMoves.entrySet()) {
                for (int i = 0; i < winRewards; i++) {
                    matchBoxes.get(e.getKey()).add(e.getValue());
                }

            }
        }
 
        if (Game.gameState().equals("its a draw")) {
            for (Map.Entry<String, Integer> e : currentGameMoves.entrySet()) {
                for (int i = 0; i < drawRewards; i++) {
                    matchBoxes.get(e.getKey()).add(e.getValue());
                }
            }
        }
        currentGameMoves.clear();
    }
    public static void putDataFromCurrentToMatchBoxesO() {
        if (Game.gameState().equals("1 has won")) {
            for (Map.Entry<String, Integer> e : currentGameMoves.entrySet()) {
                for (int i = 0; i < winRewardsO; i++) {
                    matchBoxes.get(e.getKey()).add(e.getValue());
                }

            }
        }

        if (Game.gameState().equals("its a draw")) {
            for (Map.Entry<String, Integer> e : currentGameMoves.entrySet()) {
                for (int i = 0; i < drawRewardsO; i++) {
                    matchBoxes.get(e.getKey()).add(e.getValue());
                }
            }
        }
        currentGameMoves.clear();
    }
    
    public static void incrementWins(){
        gamesWon++; 
        GameBoard.getObj().updateWinCount(gamesWon);
    }
    
    public static void incrementLosses(){
        gamesLost++;
        GameBoard.getObj().updateLossCount(gamesLost);
    }
    
    public static void incrementDraws(){
        gamesDraw++;
        GameBoard.getObj().updateDrawCount(gamesDraw);
    }
}
