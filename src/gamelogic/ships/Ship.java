package gamelogic.ships;

import gamelogic.Direction;

public abstract class Ship {
    protected String name;
    protected int xpos;
    protected int ypos;
    protected Direction direction;
    /** Represents ship condition. <br>
     * Array is the size of the ship's length in tiles. <br>
     * First array index represents top end of ship when placed vertically, or left end of
     * ship when placed horizontally. <br>
     * Entry of 0 means undamaged, 1 represents damaged/hit. */
    protected int[] shipCondition;

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
}
