package cs3500.threetrios.model;

/**
 * Represents a read-only view of the Three Trios game model.
 * This interface provides methods to retrieve the current state of the game
 * without modifying it, allowing safe access to the game's status, participants, and grid.
 */
public interface ReadOnlyThreeTriosModel {
  /**
   * Gets the current coach.
   *
   * @return the current coach
   */
  Coach getCurrentCoach();


  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  boolean isGameStarted();


  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Gets the winner of the game.
   *
   * @return the winning coach, or null if it's a tie
   */
  Coach getWinner();

  /**
   * Returns the grid.
   * @return the grid
   */
  Grid getGrid();


}
