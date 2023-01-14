package gamelogic.ships;

import gamelogic.Direction;

public abstract class Ship {
    protected String name;
    protected int xpos;
    protected int ypos;
    /** DOWN or RIGHT only */
    protected Direction direction;
    /** Represents ship condition. <br>
     * Array is the size of the ship's length in tiles. <br>
     * First array index represents top end of ship when placed vertically, or left end of
     * ship when placed horizontally. <br>
     * Entry of 0 means undamaged, 1 represents damaged/hit. */
    protected int[] shipCondition;

    /** Creates a ship instance.
     *
     * @param name   Name of the ship
     * @param length Length of the ship in tiles */
    public Ship(String name, int length) {
        this.name= name;
        shipCondition= new int[length];
    }

    /** @return length of this ship */
    public int getLength() {
        return shipCondition.length;
    }

    /** @return name of this ship */
    public String getName() {
        return name;
    }

    /** @param ypos y-position of the ship tile to check
     * @param xpos x-position of the ship tile to check
     * @return whether the tile of the ship is damaged */
    public Boolean isDamagedAt(int ypos, int xpos) {
        int offset= xpos + ypos - this.xpos - this.ypos;
        return shipCondition[offset] == 1;
    }

    /** @return whether this ship has sunk */
    public Boolean isSunk() {
        for (int i= 0; i < shipCondition.length; i++ ) {
            if (shipCondition[i] == 0)
                return false;
        }
        return true;
    }

    /** Sets x-coordinate of ship */
    public void setX(int x) {
        xpos= x;
    }

    /** Sets y-coordinate of ship */
    public void setY(int y) {
        ypos= y;
    }

    /** Sets direction of ship */
    public void setDirection(Direction dir) {
        direction= dir;
    }
}
