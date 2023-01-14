package test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gamelogic.Direction;
import main.InputParser;

/** Tests for main.InputParser */
class InputParserTests {

    @Test
    void parseValidTileE10() {
        int[] res= InputParser.parseTile(" E10 ");

        int[] expected= new int[2];
        expected[0]= 4;
        expected[1]= 9;
        assertArrayEquals(expected, res);
    }

    @Test
    void parseValidTileC2() {
        int[] res= InputParser.parseTile(" C2");

        int[] expected= new int[2];
        expected[0]= 2;
        expected[1]= 1;
        assertArrayEquals(expected, res);
    }

    @Test
    void parseInvalidTileC2A() {
        int[] res= InputParser.parseTile(" C2A");
        assertArrayEquals(null, res);
    }

    @Test
    void parseInvalidTileEmpty() {
        int[] res= InputParser.parseTile("   ");
        assertArrayEquals(null, res);
    }

    @Test
    void parseInvalidTile12A() {
        int[] res= InputParser.parseTile(" 12A  ");
        assertArrayEquals(null, res);
    }

    @Test
    void parseInvalidTileA() {
        int[] res= InputParser.parseTile(" A  ");
        assertArrayEquals(null, res);
    }

    @Test
    void parseInvalidTile1() {
        int[] res= InputParser.parseTile(" 1  ");
        assertArrayEquals(null, res);
    }

    @Test
    void parseDirectionN() {
        Direction dir= InputParser.parseDirection(" n  ");
        assertEquals(Direction.UP, dir);
    }

    @Test
    void parseDirectionE() {
        Direction dir= InputParser.parseDirection(" E  ");
        assertEquals(Direction.RIGHT, dir);
    }

    @Test
    void parseDirectionEmpty() {
        Direction dir= InputParser.parseDirection("  ");
        assertEquals(null, dir);
    }

    @Test
    void parseDirectionNortheast() {
        Direction dir= InputParser.parseDirection(" Northeast  ");
        assertEquals(null, dir);
    }
}
