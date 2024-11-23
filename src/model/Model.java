package model;


import java.util.List;

/**
 * Represents the game model.
 */
public interface Model extends ModelReadOnly {

  /**
   * Starts the game with the given card index, grid col, and grid row.
   * @param grid    the gamegrid for three trios
   * @param cards   the deck of cards to be used
   * @param referee the referee that manages game logic
   */
  void startGame(Grid grid, List<Card> cards, Referee referee);

  /**
   * Places a card on the grid at the specified row and column, from an idx in the coach's
   * hand.
   * @param idx the index of the card in the coach's hand
   * @param row the row on the grid
   * @param col the column on the grid
   * @throws IllegalStateException    if the game has not started or is over
   * @throws IllegalArgumentException if the index, row, or column is out of bounds
   * @implNote uses 0-based indexing for grid coordinates and hand index.
   */
  void placeCard(int idx, int row, int col) throws IllegalStateException,
      IllegalArgumentException;


}
