package cs3500.threetrios.model;

/**
 * to represent the grid-shaped board of the game three-trios.
 */
public class Grid {
  private final AGridCell[][] grid; // first index is rows, second is columns, obvious index at 0
  private final int numHoles;

  /**
   * Constructs a Grid.
   * @param grid - the grid to construct
   */
  public Grid(AGridCell[][] grid) {
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
    for (AGridCell[] row : grid) {
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
  AGridCell placeCardOn(int row, int col, Card card) {
    AGridCell relevantCell = grid[row][col];
    relevantCell.placeCard(card);
    return relevantCell;
  }

  @Override
  public String toString() {
    String out = grid.length + " " + grid[0].length + "\n";
    for (AGridCell[] row : grid) {
      for (AGridCell cell : row) {
        out += cell.renderTextConstructor();
      }
      out += "\n";
    }
    return out.stripTrailing();
  }

  /**
   * Whether all cells in this grid are filled with cards.
   * @return - whether all card cells in this grid are filled with cards
   */
  public boolean isFull() {
    for (AGridCell[] row : grid) {
      for (AGridCell cell : row) {
        if (!cell.hasCard()) {
          return false;
        }
      }
    }
    return true;
  }

  public int getNumHoles() {
    return this.numHoles;
  }

  /**
   * The grid of this.
   * @return - the grid of this.
   */
  public AGridCell[][] arrayRepr() {
    return this.grid;
  }
}
