package Background;

/** Represents a player in the game */
public class Player {
    /** Player's username */
    private String name;
    /** Gameboard representing this player's ships */
    private Board gameboard;

    public Player(String name, Board board) {
        this.name= name;
        gameboard= board;
    }
}
