package ui;

import java.util.Scanner;

import gamelogic.Player;
import gamelogic.ships.Ship;

public class GameUI {

    /** Begins the game and runs until the game ends
     *
     * @param scan      scanner to take user input
     * @param playerOne first player of game
     * @param playerTwo second player of game */
    public static void executeGame(Scanner scan, Player playerOne, Player playerTwo) {
        Boolean isGameOver= false;
        Boolean isPlayerOneTurn= true;

        while (!isGameOver) {
            if (isPlayerOneTurn) {
                executeTurn(scan, playerOne, playerTwo);
            } else {
                executeTurn(scan, playerTwo, playerOne);
            }

            if (playerOne.getBoard().allShipsSunk() ||
                playerTwo.getBoard().allShipsSunk())
                isGameOver= true;

            isPlayerOneTurn= !isPlayerOneTurn;
        }

        congratulateWinner(playerOne, playerTwo);
    }

    /** Congratulates the winner of the game.
     *
     * @param playerOne first player of game
     * @param playerTwo second player of game */
    private static void congratulateWinner(Player playerOne, Player playerTwo) {
        Player winningPlayer= playerOne;
        if (playerOne.getBoard().allShipsSunk()) winningPlayer= playerTwo;

        String endResult= String.format("%s has sunk all of the ships and won the game!",
            winningPlayer.getName());

        System.out.println("\n\n");
        System.out.println("====================");
        System.out.println(endResult);
    }

    /** Executes a turn for one player.
     *
     * @param scan    scanner to take user input
     * @param current player to execute the turn for
     * @param enemy   enemy against the player with the current turn */
    private static void executeTurn(Scanner scan, Player current, Player enemy) {
        // Make Header
        String header= String.format("%s's TURN", current.getName());
        System.out.println("====================");
        System.out.println(header);
        System.out.println("====================\n\n");

        // Print boards
        String ownBoard= current.getBoard().toString(false);
        System.out.println("Your board:");
        System.out.println(ownBoard);

        System.out.print("\n");

        System.out.println("Enemy board:");
        String enemyBoard= enemy.getBoard().toString(true);
        System.out.println(enemyBoard);

        System.out.print("\n");

        // Ask where to shoot
        int[] coords= null;
        while (coords == null) {
            String attackQuestion= String.format(
                "%s: Which tile do you want to fire at?",
                current.getName());
            System.out.println(attackQuestion);

            String attackTileAnswer= scan.next();

            coords= InputParser.parseTile(attackTileAnswer,
                enemy.getBoard().getHeight(),
                enemy.getBoard().getWidth());

            if (coords == null) System.out.println("Invalid tile. Please try again");
        }

        // handle attack result
        Ship attackedShip= enemy.getBoard().attack(coords[0], coords[1]);
        if (attackedShip == null) {
            System.out.println("Miss!");
        } else {
            System.out.println("Hit!");
            if (attackedShip.isSunk()) {
                String sinkLine= String.format("%s has sunk a %s!", current.getName(),
                    attackedShip.getName());
                System.out.println(sinkLine);
            }
        }

    }

}
