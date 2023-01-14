package ui;

import gamelogic.Direction;

/** Class with functions to parse user input. */
public class InputParser {
    /** Parses a user input string into board coordinates as defined in gamelogic.Board
     * <br>
     * User input is in the form of a letter followed by a number, ex. "C10". Case
     * insensitive.
     *
     * @param input     User input string
     * @param maxHeight Maximum board width
     * @param maxWidth  Maximum board height
     *
     * @return pair where first element is y-coord, second element is x-coord, or null if
     *         unparseable/out-of-bounds. */
    public static int[] parseTile(String input, int maxHeight, int maxWidth) {
        input= input.strip().toLowerCase();

        if (input.isEmpty()) return null;

        char letter= input.charAt(0);
        if (letter < 'a' || letter > 'z') return null;
        int yIndex= letter - 'a';

        int xIndex= 0;
        try {
            xIndex= Integer.parseInt(input.substring(1)) - 1;
        } catch (Exception e) {
            return null;
        }

        if (xIndex < 0 || yIndex < 0 || xIndex >= maxWidth || yIndex >= maxHeight)
            return null;

        int[] coords= new int[2];
        coords[0]= yIndex;
        coords[1]= xIndex;
        return coords;
    }

    /** Parses a user input string into a direction <br>
     * User input is in the form of "N", "E", "S", or "W". Case insensitive.
     *
     * @param input User input string
     *
     * @return a direction from gamelogic.Direction, or null if unparseable */
    public static Direction parseDirection(String input) {
        input= input.strip().toLowerCase();

        if (input.isEmpty()) return null;

        switch (input) {
        case "n":
            return Direction.UP;
        case "e":
            return Direction.RIGHT;
        case "s":
            return Direction.DOWN;
        case "w":
            return Direction.LEFT;
        default:
            return null;
        }
    }
}
