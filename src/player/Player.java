package player;

/**
 * Represents a player in the game.
 */
public interface Player {

  /**
   * returns the name of the player.
   *
   * @return the player's name
   * @throws IllegalStateException if not initialized
   */
  String getName();

  Difficulty difficulty();

  Move nextMove();

}
