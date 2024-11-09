package cs3500.threetrios.model;

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
