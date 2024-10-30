package cs3500.threetrios.model;


import java.util.List;

/**
 * Represents the game model.
 */
public interface IModel {
  /**
   * Starts the game with the given card index, grid col, and grid row.
   *
   * @param grid the grid
   * @param cards the cards
   * @param referee the referee
   * @throws IllegalArgumentException if the grid is null
   */
  void startGame(Grid grid, List<Card> cards, BattlePhaseReferee referee);

  /**
   * Places a card on the grid for the given coach.
   *
   * @param idx represents the card index
   * @param row represents the row
   * @param col represents the col
   *

   */
  void placeCard(int idx, int row, int col);

  /**
   * Gets the current coach.
   *
   * @return the current coach
   */
  Coach getCurrentCoach();

  /**
   * Advances to the next coach's turn.
   */
  void nextCoachTurn();

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

  Grid getGrid();

}

