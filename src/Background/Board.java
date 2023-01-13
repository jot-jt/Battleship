package Background;

import java.util.ArrayList;

/** Represents the gameboard that holds ship location, as well as enemy hits, misses, and
 * sinks for a single player. */
public class Board {
    /** Represents a player's gameboard. <br>
     * Values > 1 represents a ship corresponding to the 1-based index of the ship in
     * this.ships. <br>
     * Value of 0 represents an empty, untouched tile. <br>
     * Value of -1 represents a miss. */
    private int[][] board;
    private ArrayList<Ship> ships;

    public Board(int length, int width) {
        board= new int[length][width];
    }

    /** Checks whether the specified board tiles are empty, starting from a specific tile
     * and moving along a direction for a certain length. <br>
     *
     * @param x         x-coordinate of the beginning tile
     * @param y         y-coordinate of the beginning tile
     * @param direction direction starting from the beginning tile to iterate over
     * @param length    number of tiles to check in the given direction (>= 0)
     *
     * @return whether all the tiles in the specified range are empty and within
     *         bounds. */
    private Boolean checkTilesEmpty(int x, int y, Direction direction, int length) {
        switch (direction) {
        case UP:
            y= y - length;
        case DOWN:
            for (int j= y; j < y + length; j++ ) {
                if (j > board.length || board[x][j] != 0) return false;
            }
            return true;
        case LEFT:
            x= x - length;
        case RIGHT:
            for (int i= x; i < x + length; i++ ) {
                if (i > board[0].length || board[i][y] != 0) return false;
            }
            return true;
        }
        return false;
    }

    /** Sets the value of all specified board tiles to a target value, starting from a
     * specific tile and moving along a direction for a certain length. <br>
     *
     * @param x           x-coordinate of the beginning tile
     * @param y           y-coordinate of the beginning tile
     * @param direction   direction starting from the beginning tile to set values for
     * @param length      number of tiles to check in the given direction (>= 0)
     * @param targetValue the number to set all tiles in the specified range to */
    private void setTile(int x, int y, Direction direction, int length,
        int targetValue) {
        switch (direction) {
        case UP:
            y= y - length;
        case DOWN:
            for (int j= y; j < y + length; j++ ) {
                board[x][j]= targetValue;
            }
            break;
        case LEFT:
            x= x - length;
        case RIGHT:
            for (int i= x; i < x + length; i++ ) {
                board[i][y]= targetValue;
            }
            break;
        }
    }

    /** Adds a ship onto the player's gameboard.
     *
     * @param ship      ship to add to the gameboard
     * @param x         x-coordinate of the beginning tile
     * @param y         y-coordinate of the beginning tile
     * @param direction direction from the beginning tile to place the ship in
     *
     * @return True if success, False otherwise */
    public Boolean addShip(Ship ship, int x, int y, Direction direction) {
        boolean tilesEmpty= checkTilesEmpty(x, y, direction, ship.getLength());
        if (tilesEmpty) {
            ships.add(ship);
            int ship_index= ships.size(); // 1-based index
            setTile(x, y, direction, ship.getLength(), ship_index);
            return true;
        }
        return false;
    }
}
