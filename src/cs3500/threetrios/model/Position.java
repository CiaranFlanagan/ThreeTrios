package cs3500.threetrios.model;

/**
 * Represents a position on the grid in the Three Trios game.
 * A Position is defined by its row and column coordinates.
 */
public final class Position {
  private int row;
  private int col;

  /**
   * Private constructor to create a Position with specified row and column.
   *
   * @param row the row index of the position
   * @param col the column index of the position
   */
  private Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Creates and returns a Position instance with the specified row and column.
   *
   * @param row the row index of the position
   * @param col the column index of the position
   * @return a new Position instance with the specified row and column
   */
  public static Position create(int row, int col) {
    return new Position(row, col);
  }

  public int row() {
    return row;
  }

  public int col() {
    return col;
  }

}
