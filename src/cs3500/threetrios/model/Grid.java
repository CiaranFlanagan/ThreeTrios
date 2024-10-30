package cs3500.threetrios.model;

/**
 * to represent the grid-shaped board of the game three-trios
 */
public class Grid {
  private final AGridCell[][] grid; // at least 2x2
  public Grid(AGridCell[][] grid) {
    this.grid = grid;
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
    return out;
  }
}
