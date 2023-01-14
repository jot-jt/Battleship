package main;

import gamelogic.Board;
import gamelogic.Player;
import ui.ShipPlacer;

public class Main {

    private static int BOARD_WIDTH= 10;
    private static int BOARD_HEIGHT= 10;

    public static void main(String[] args) {

        Board gameboardOne= new Board(BOARD_WIDTH, BOARD_HEIGHT);
        Board gameboardTwo= new Board(BOARD_WIDTH, BOARD_HEIGHT);

        Player playerOne= new Player("Player 1", gameboardOne, gameboardTwo);
        Player playerTwo= new Player("Player 2", gameboardTwo, gameboardOne);

        ShipPlacer.placeShipsOnBoard(playerOne);
        ShipPlacer.placeShipsOnBoard(playerTwo);
    }

}
