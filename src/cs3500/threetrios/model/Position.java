package cs3500.threetrios.model;

/**
 * to represent a position on the grid
 */
public final class Position {
  private int row;
  private int col;

  private Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public static Position of(int row, int col) {
    return new Position(row, col);
  }

  public int row() {
    return row;
  }

  public int col() {
    return col;
  }

}
