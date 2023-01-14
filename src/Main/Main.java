package main;

import gamelogic.Board;
import gamelogic.Player;

public class Main {

    private static int BOARD_WIDTH= 10;
    private static int BOARD_HEIGHT= 10;

    public static void main(String[] args) {
        Board gameboard1= new Board(BOARD_WIDTH, BOARD_HEIGHT);
        Player player1= new Player("Player 1", gameboard1);

        Board gameboard2= new Board(BOARD_WIDTH, BOARD_HEIGHT);
        Player player2= new Player("Player 2", gameboard2);

        System.out.println(gameboard1);
    }

}
