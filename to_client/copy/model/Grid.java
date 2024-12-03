package copy.model;

/**
 * To represent a grid of cards in Three Trios.
 */
public interface Grid {

  /**
   * Checks if all cells in the grid are filled with cards.
   * @return true if the grid is fully occupied, false otherwise
   */
  boolean isFull();

  /**
   * Gets the number of holes in the grid.
   * @return the number of holes
   */
  int getNumHoles();

  /**
   * Provides a read-only view of the grid as a 2D array.
   * @return a 2D array of GridCellReadOnly
   */

  GridCellReadOnly[][] readOnlyArray2D();

  /**
   * To produce the number of rows of this grid.
   * @return the number of rows of this grid.
   */
  int numRows();

  /**
   * To produce the number of columns of this grid.
   * @return the number of columns of this grid.
   */
  int numCols();

}
