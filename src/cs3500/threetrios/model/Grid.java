package cs3500.threetrios.model;

import java.util.function.Supplier;

/**
 * Represents the grid-shaped board for the game Three Trios.
 * The grid is made up of cells, some of which can hold cards and others which may represent holes.
 */
public final class Grid {
  private final GridCellAbstract[][] grid; // first index is rows, second is columns, obvious
  // index at 0
  private final int numHoles;

  /**
   * Constructs a Grid with a given 2D array of GridCellAbstract objects.
   * Calculates the number of holes (cells that cannot hold cards) on the grid.
   *
   * @param grid the grid to initialize
   * @throws IllegalArgumentException if the grid is null or has non-positive dimensions
   */
  public Grid(GridCellAbstract[][] grid) {
    if (grid == null) {
      throw new IllegalArgumentException();
    }
    if (grid.length < 1 || grid[0].length < 1) {
      throw new IllegalArgumentException("Rows and Cols must be positive integers");
    }
    this.grid = grid;
    int numHoles = 0;
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (!grid[row][col].canHaveCard()) {
          numHoles += 1;
        }
      }
    }
    this.numHoles = numHoles;
    linkRows();
    linkCols();
  }

  // to link the cells in each row horizontally
  private void linkRows() {
    for (GridCellAbstract[] row : grid) {
      for (int curCol = 1; curCol < grid[0].length; curCol++) {
        row[curCol].link(row[curCol - 1], CardinalDirection.WEST);
      }
    }
  }

  // to link the cells in each column vertically
  private void linkCols() {
    for (int curRow = 1; curRow < grid.length; curRow++) {
      for (int curCol = 0; curCol < grid[0].length; curCol++) {
        grid[curRow][curCol].link(grid[curRow - 1][curCol], CardinalDirection.NORTH);
      }

    }
  }

  // to place [card] at [row], [col] on this
  // index at 0
  GridCellAbstract placeCardOn(int row, int col, Card card) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
      throw new IllegalArgumentException("Rows and Cols must be positive integers");
    }
    GridCellAbstract relevantCell = grid[row][col];
    relevantCell.placeCard(card);
    return relevantCell;
  }

  @Override
  public String toString() {
    String out = grid.length + " " + grid[0].length + "\n";
    for (GridCellAbstract[] row : grid) {
      for (GridCellAbstract cell : row) {
        out += cell.renderTextConstructor();
      }
      out += "\n";
    }
    return out.stripTrailing();
  }

  /**
   * Whether all cells in this grid are filled with cards.
   *
   * @return - whether all card cells in this grid are filled with cards
   */
  public boolean isFull() {
    for (GridCellAbstract[] row : grid) {
      for (GridCellAbstract cell : row) {
        if (!cell.hasCard()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * The number of holes in this.
   *
   * @return - the number of holes in this
   */
  public int getNumHoles() {
    return this.numHoles;
  }

  /**
   * The grid of cards with no cards represented as null.
   *
   * @return - the grid of this.
   * @implNote - possible effect can still occur to cells if casting is used, hack with caution.
   */
  public GridCellReadOnly[][] readOnly2dCellArray() {
    return this.grid;
  }

  /**
   * Provides a Supplier for a new Grid instance, replicating the current grid’s configuration.
   * Cells in the replicated grid are based on the structure of this grid.
   *
   * @return a Supplier that provides a new Grid instance with similar configuration
   */
  public Supplier<Grid> supply() {
    Supplier<GridCellAbstract[][]> a = () ->
    {
      GridCellAbstract[][] grid = new GridCellAbstract[this.grid.length][this.grid[0].length];
      for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
          //grid[row][col] = this.grid[row][col]
        }
      }
      return null;
    };
    return null;
  }
}
