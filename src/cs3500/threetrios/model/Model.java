package cs3500.threetrios.model;


import java.util.List;

/**
 * Represents the game model.
 */
public interface Model extends ReadOnlyThreeTriosModel {
  /**
   * Starts the game with the given card index, grid col, and grid row.
   *
   * @param grid the grid
   * @param cards the cards
   * @param referee the referee
   */
  void startGame(Grid grid, List<Card> cards, Referee referee);

  /**
   * Places a card on the grid at the specified row and column, from an idx in the coach's hand.
   *
   * @param idx index of move
   * @param row row on grid
   * @param col col on grid
   * @throws IllegalStateException if game is not started/game over
   * @throws IllegalArgumentException if invalid move
   * @implNote 0 indexing
   */
  void placeCard(int idx, int row, int col) throws IllegalStateException, IllegalArgumentException;

}

