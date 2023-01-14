package ui;

import java.util.ArrayList;
import java.util.Scanner;

import gamelogic.Direction;
import gamelogic.Player;
import gamelogic.ships.AircraftCarrier;
import gamelogic.ships.Battleship;
import gamelogic.ships.Cruiser;
import gamelogic.ships.Destroyer;
import gamelogic.ships.Ship;
import gamelogic.ships.Submarine;

/** Class to handle how players place the ships on their game board */
public class ShipPlacer {

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

    /** Continually asks user until they give a valid ship location for one end.
     *
     * @param scan   input reader
     * @param player user to ask
     * @param ship   ship to inquire on
     * @return (y,x) coordinates of location on board. */
    private static int[] askShipLocation(Scanner scan, Player player, Ship ship) {
        int[] coords= null;
        while (coords == null) {
            String locQuestion= String.format(
                "%s: What tile to place one end of the %s (length %d)?",
                player.getName(), ship.getName(), ship.getLength());
            System.out.println(locQuestion);

            String tileAnswer= scan.next();

            int height= player.getBoard().getHeight();
            int width= player.getBoard().getWidth();
            coords= InputParser.parseTile(tileAnswer, height, width);

            if (coords == null) {
                System.out.println("Invalid tile. Please try again.");
            }
        }
        return coords;
    }

    /** Continually asks user until they give valid direction to point the ship towards.
     *
     * @param scan   input reader
     * @param player user to ask
     * @param ship   ship to inqure on
     * @return direction to point ship towards */
    private static Direction askShipDirection(Scanner scan, Player player, Ship ship) {

        Direction direction= null;
        while (direction == null) {
            String dirQuestion= String.format(
                "%s: Which direction to point the %s towards? (N/E/S/W)",
                player.getName(), ship.getName());
            System.out.println(dirQuestion);

            String directionAnswer= scan.next();
            direction= InputParser.parseDirection(directionAnswer);

            if (direction == null) {
                System.out.println("Invalid direction. Please try again.");
            }
        }
        return direction;
    }

    /** Begins user interface for player to add ships to their gameboard
     *
     * @param player: the user to ask */
    public static void placeShipsOnBoard(Player player) {
        ArrayList<Ship> ships= generateShips();

        Scanner scan= new Scanner(System.in);

        for (int i= 0; i < ships.size(); i++ ) {
            Ship ship= ships.get(i);

            Boolean success= false;
            while (!success) {
                Direction dir= null;
                int[] coords= askShipLocation(scan, player, ship);
                if (!(ship instanceof Submarine)) {
                    dir= askShipDirection(scan, player, ship);
                }

                success= player.getBoard().addShip(ship, coords[0], coords[1], dir);
                if (!success)
                    System.out.println("Invalid ship position. Please try again.");
            }
        }
        scan.close();
    }
}
