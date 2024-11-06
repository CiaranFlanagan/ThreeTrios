package cs3500.threetrios.model;


import java.util.List;

/**
 * Represents the game model.
 */
public interface Model extends ReadOnlyThreeTriosModel{
  /**
   * Starts the game with the given card index, grid col, and grid row.
   *
   * @param grid the grid
   * @param cards the cards
   * @param referee the referee
   */
  void startGame(Grid grid, List<Card> cards, Referee referee);

  /**
   * Places a card from the current coach on the grid.
   *
   * @param idx represents the card index
   * @param row represents the row (0 index)
   * @param col represents the col (0 index)
   *
   */
  void placeCard(int idx, int row, int col);

}

