package Background;

import java.util.ArrayList;

import Background.Ships.Ship;

/** Represents the gameboard that holds ship location, as well as enemy hits, misses, and
 * sinks for one player. */
public class Board {

    /** Represents a player's gameboard where each entry is a game tile. <br>
     * Tiles with values >= 1 represent part of a ship. Specific value is the 1-based
     * index of the corresponding ship in this.ships. <br>
     * Tiles with values of 0 represent an empty, untouched tile. <br>
     * Values of -1 represent misses. */
    private int[][] board;

    /** Holds all ships on the gameboard. */
    private ArrayList<Ship> ships;

    /** Constants to represent tile state */
    public final int EMPTY= 0;
    public final int MISS= -1;

    /** Creates a gameboard instance.
     *
     * @param height size of the board's vertical axis
     * @param width  size of the board's horizontal axis */
    public Board(int height, int width) {
        board= new int[height][width];
    }

    /** Checks whether the specified board tiles are empty. Begins from a specific tile
     * and continues a certain number of tiles along one direction. <br>
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

    /** Sets the value of all specified board tiles to a target value. Begins from a
     * specific tile and continues a certain number of tiles along one direction. <br>
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
     * @param x         x-coordinate of the tile to place one end of ship
     * @param y         y-coordinate of the tile to place one end of ship
     * @param direction direction from the specified tile to place the ship in
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
