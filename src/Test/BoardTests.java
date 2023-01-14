package test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gamelogic.Board;
import gamelogic.Direction;
import gamelogic.ships.Destroyer;
import gamelogic.ships.Submarine;

class BoardTests {

    @Test
    void placeOneSubmarineInBoundsTest() {
        Board gameboard= new Board(2, 2);
        Submarine sub= new Submarine();
        gameboard.addShip(sub, 1, 0, Direction.DOWN);

        int[][] correctArr= new int[2][2];
        correctArr[1][0]= 1;
        assertArrayEquals(correctArr, gameboard.board);
    }

    @Test
    void placeDestroyerOutOfBoundsTest() {
        Board gameboard= new Board(2, 2);
        Destroyer des= new Destroyer();
        Boolean success= gameboard.addShip(des, 1, 1, Direction.DOWN);
        assertEquals(success, false);
    }

    @Test
    void placeDestroyerHorizontallyInBoundsTest() {
        Board gameboard= new Board(2, 2);
        Destroyer des= new Destroyer();
        gameboard.addShip(des, 1, 1, Direction.LEFT);

        int[][] correctArr= new int[2][2];
        correctArr[1][0]= 1;
        correctArr[1][1]= 1;
        assertArrayEquals(correctArr, gameboard.board);
    }

    @Test
    void placeDestroyerVerticallyInBoundsTest() {
        Board gameboard= new Board(2, 2);
        Destroyer des= new Destroyer();
        gameboard.addShip(des, 1, 0, Direction.UP);

        int[][] correctArr= new int[2][2];
        correctArr[0][0]= 1;
        correctArr[1][0]= 1;
        assertArrayEquals(correctArr, gameboard.board);
    }
}
