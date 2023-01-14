package main;

import java.util.ArrayList;
import java.util.Scanner;

import gamelogic.Board;
import gamelogic.Direction;
import gamelogic.Player;
import gamelogic.ships.AircraftCarrier;
import gamelogic.ships.Battleship;
import gamelogic.ships.Cruiser;
import gamelogic.ships.Destroyer;
import gamelogic.ships.Ship;
import gamelogic.ships.Submarine;

public class Main {

    private static int BOARD_WIDTH= 10;
    private static int BOARD_HEIGHT= 10;

    /** @return a list of ships to be placed on the player's board. <br>
     *         Contains 1 Aircraft Carrier, 1 Battleship, 1 Cruiser, 2 Destroyers, 2
     *         Submarines */
    private static ArrayList<Ship> generateShips() {
        ArrayList<Ship> unplaced_ships= new ArrayList<>();
        unplaced_ships.add(new AircraftCarrier());
        unplaced_ships.add(new Battleship());
        unplaced_ships.add(new Cruiser());
        unplaced_ships.add(new Destroyer());
        unplaced_ships.add(new Destroyer());
        unplaced_ships.add(new Submarine());
        unplaced_ships.add(new Submarine());
        return unplaced_ships;
    }

    private static void askTileLocation()

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);

        Board gameboard1= new Board(BOARD_WIDTH, BOARD_HEIGHT);
        Player playerOne= new Player("Player 1", gameboard1);

        Board gameboard2= new Board(BOARD_WIDTH, BOARD_HEIGHT);
        Player playerTwo= new Player("Player 2", gameboard2);

        ArrayList<Ship> playerOneShips= generateShips();

        // Player 1: add ships to board
        for (int i= 0; i < playerOneShips.size(); i++ ) {
            Ship ship= playerOneShips.get(i);

            // Ask for ship location
            String locQuestion= String.format(
                "What tile to place one end of the %s (length %d)?",
                ship.getName(), ship.getLength());
            System.out.println(locQuestion);
            String ans= scan.next();

            // Ask for ship direction
            if (!(ship instanceof Submarine)) {
                String dirQuestion= String.format(
                    "Which direction to point the %s towards? (N/E/S/W)",
                    ship.getName());
                System.out.println(dirQuestion);
                String ans2= scan.next();
            }
        }
    }

}
