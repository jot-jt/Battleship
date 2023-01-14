package gamelogic;

/** Represents a player in the game */
public class Player {
    /** Player's username */
    private String name;
    /** Gameboard representing this player's ships */
    private Board gameboard;
    /** Gameboard representing enemy player's ships */
    private Board enemyBoard;

    /** Create a player instance.
     *
     * @param name       player's username
     * @param board      gameboard that represents this player's ships
     * @param enemyBoard gameboard that represents thie enemy's ships */
    public Player(String name, Board board, Board enemyBoard) {
        this.name= name;
        gameboard= board;
        this.enemyBoard= enemyBoard;
    }

    /** @return the player's gameboard */
    public Board getBoard() {
        return gameboard;
    }

    /** @return the player's username */
    public String getName() {
        return name;
    }
}
