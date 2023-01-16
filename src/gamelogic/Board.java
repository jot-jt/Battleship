package gamelogic;

import java.util.ArrayList;

import gamelogic.ships.Ship;

/** Represents the gameboard that holds ship location, as well as enemy hits, misses, and
 * sinks for one player. */
public class Board {

    /** Represents a player's gameboard where each entry is a game tile. <br>
     * Tiles with values >= 1 represent part of a ship. Specific tile value is the 1-based
     * index of the corresponding ship in this.ships. <br>
     * Tiles with values of 0 represent an untouched water tile. <br>
     * Values of -1 represent misses.<br>
     * Array coordinates are in the format (y,x). (0,0) is defined to be the top left
     * corner of the board. The y-axis increases down. The x-axis increases right. */
    public int[][] board;

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
        ships= new ArrayList<>();
    }

    /** Checks whether the specified board tiles are empty. Begins from a specific tile
     * and walks a certain number of tiles in one direction. <br>
     *
     * @param y         y-coordinate of the beginning tile
     * @param x         x-coordinate of the beginning tile
     * @param direction direction starting from the beginning tile to iterate over
     * @param length    number of tiles to check in the given direction (>= 0)
     *
     * @return whether all the tiles in the specified region are empty and within
     *         bounds. */
    private Boolean checkTilesEmpty(int y, int x, Direction direction, int length) {
        int offset= length - 1;
        switch (direction) {
        case UP:
            y= y - offset;
        case DOWN:
            for (int j= y; j < y + length; j++ ) {
                if (j < 0 || j >= board.length || board[j][x] != 0) return false;
            }
            return true;
        case LEFT:
            x= x - offset;
        case RIGHT:
            for (int i= x; i < x + length; i++ ) {
                if (i < 0 || i >= board[0].length || board[y][i] != 0) return false;
            }
            return true;
        }
        return false;
    }

    /** Sets the value of all specified board tiles to a target value. Begins from a
     * specific tile and walks a certain number of tiles along one direction. <br>
     *
     * @param y           y-coordinate of the beginning tile
     * @param x           x-coordinate of the beginning tile
     * @param direction   direction starting from the beginning tile to set values for
     * @param length      number of tiles to check in the given direction (>= 0)
     * @param targetValue the number to set all tiles in the specified range to */
    public void setTile(int y, int x, Direction direction, int length,
        int targetValue) {
        int offset= length - 1;
        switch (direction) {
        case UP:
            y= y - offset;
        case DOWN:
            for (int j= y; j < y + length; j++ ) {
                board[j][x]= targetValue;
            }
            break;
        case LEFT:
            x= x - offset;
        case RIGHT:
            for (int i= x; i < x + length; i++ ) {
                board[y][i]= targetValue;
            }
            break;
        }
    }

    /** Sets the value of the specified board tiles to a target value.
     *
     * @param y           y-coordinate of the beginning tile
     * @param x           x-coordinate of the beginning tile
     * @param targetValue the number to set all tiles in the specified range to */
    public void setTile(int y, int x, int targetValue) {
        setTile(y, x, Direction.DOWN, 1, targetValue);
    }

    /** Updates a tile that has been fired at.
     *
     * @param y           y-coordinate of the beginning tile
     * @param x           x-coordinate of the beginning tile
     * @param targetValue the number to set all tiles in the specified range to
     *
     * @return ship that has been hit or null if miss */
    public Ship attack(int y, int x) {
        if (board[y][x] > 0) {
            // ship hit
            int ship_idx= board[y][x] - 1;
            ships.get(ship_idx).attack(y, x);

            return ships.get(ship_idx);
        }

        // miss
        setTile(y, x, Direction.DOWN, 1, MISS);
        return null;
    }

    /** @return whether all ships on this board have sunk */
    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) return false;
        }
        return true;
    }

    /** Adds a ship onto the player's gameboard. Also updates the ship's properties based
     * on the board position.
     *
     * @param ship      ship to add to the gameboard
     * @param y         y-coordinate of the tile to place one end of ship
     * @param x         x-coordinate of the tile to place one end of ship
     * @param direction direction from the specified tile to place the ship in
     *
     * @return True if success, False otherwise */
    public Boolean addShip(Ship ship, int y, int x, Direction direction) {
        boolean tilesEmpty= checkTilesEmpty(y, x, direction, ship.getLength());
        if (tilesEmpty) {
            ships.add(ship);
            switch (direction) {
            case DOWN:
            case RIGHT:
                ship.setY(y);
                ship.setX(x);
                ship.setDirection(direction);
                break;
            case LEFT:
                ship.setX(x - ship.getLength() + 1);
                ship.setY(y);
                ship.setDirection(Direction.RIGHT);
                break;
            case UP:
                ship.setX(x);
                ship.setY(y - ship.getLength() + 1);
                ship.setDirection(Direction.DOWN);
                break;
            }
            int ship_index= ships.size(); // 1-based index
            setTile(y, x, direction, ship.getLength(), ship_index);
            return true;
        }
        return false;
    }

    /** @return board's width */
    public int getWidth() {
        return board[0].length;
    }

    /** @return board's height */
    public int getHeight() {
        return board.length;
    }

    /** @param enemyPOV true if enemy is viewing this board
     * @return the string representation of the gameboard */
    public String toString(Boolean enemyPOV) {
        StringBuilder sb= new StringBuilder();

        // horizontal-axis markers
        sb.append("    1  2  3  4  5  6  7  8  9  10\n");

        for (int i= 0; i < board.length; i++ ) {
            int[] line= board[i];

            // vertical-axis marker
            int char_int= 'A' + i;
            sb.append(" ");
            sb.append((char) char_int);
            sb.append(" ");

            for (int j= 0; j < line.length; j++ ) {
                switch (line[j]) {
                case EMPTY:
                    sb.append(" _ ");
                    break;
                case MISS:
                    sb.append(" O ");
                    break;
                default: // ship
                    int shipIndex= line[j] - 1;
                    Ship ship= ships.get(shipIndex);
                    if (ship.isSunk()) {
                        sb.append(" S ");
                    } else if (ship.isDamagedAt(i, j)) {
                        sb.append(" X ");
                    } else {
                        if (enemyPOV)
                            sb.append(" _ ");
                        else
                            sb.append(" B ");
                    }
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
