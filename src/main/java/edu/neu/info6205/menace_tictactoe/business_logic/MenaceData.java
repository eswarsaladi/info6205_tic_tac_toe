/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.info6205.menace_tictactoe.business_logic;

import java.util.*;

/**
 *
 * @author eswar
 */
public class MenaceData {

    private static MenaceData menaceData;

    public Map<String, ArrayList<Integer>> menace_store1 = new HashMap<>();
    public Map<String, ArrayList<Integer>> menace_store2 = new HashMap<>();
    ;
    public Map<String, Integer> current_game1 = new HashMap<>();
    ;
    public Map<String, Integer> current_game2 = new HashMap<>();

    ;

    public void ifDataIsAbsent(String boardState, int player) {
        if (player == 1) {
            menace_store1.putIfAbsent(boardState, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        } else if (player == 2) {
            menace_store2.putIfAbsent(boardState, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        }
    }

    public int sizeOfArray(String boardState, int player) {
        if (player == 1) {
            return menace_store1.get(boardState).size();
        } else if (player == 2) {
            return menace_store2.get(boardState).size();
        }
        return -1;
    }

    public Integer getValOfElement(String boardState, int player, int loc) {
        if (player == 1) {
            return menace_store1.get(boardState).get(loc);
        } else if (player == 2) {
            return menace_store2.get(boardState).get(loc);
        }
        return -1;
    }

    public void newGame() {
        current_game1.clear();
        current_game2.clear();
    }

    // win = 4, draw = 2, lose = 0 is the gamestate
    public void addFromCurrentToStore(int player, int gameState) {
        if (player == 1) {
            for (Map.Entry<String, Integer> entry : current_game1.entrySet()) {
                for (int i = 0; i < gameState; i++) {
                    menace_store1.get(entry.getKey()).add(entry.getValue());
                }
            }
            current_game1.clear();
        } else if (player == 2) {
            for (Map.Entry<String, Integer> entry : current_game2.entrySet()) {
                for (int i = 0; i < gameState; i++) {
                    menace_store2.get(entry.getKey()).add(entry.getValue());
                }
            }
            current_game2.clear();
        }
    }

    public void addToCurrentGame(int player, String boardState, Integer board_location) {
        if (player == 1) {
            current_game1.put(boardState, board_location);
        } else if (player == 2) {
            current_game2.put(boardState, board_location);
        }
    }

    public static MenaceData getObj() {
        if (menaceData == null) {
            menaceData = new MenaceData();
        }
        return menaceData;
    }

}
