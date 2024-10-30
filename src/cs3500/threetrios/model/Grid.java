package cs3500.threetrios.model;

/**
 * to represent the grid-shaped board of the game three-trios.
 */
public class Grid {
  private final AGridCell[][] grid; // at least 2x2

  /**
   * Constructs a Grid.
   * @param grid - the grid to construct
   */
  public Grid(AGridCell[][] grid) {
    if (grid.length < 1 || grid[0].length < 1) {
      throw new IllegalArgumentException("Rows and Cols must be positive integers");
    }
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
    return out.stripTrailing();
  }

  /**
   * Whether all cells in this grid are filled with cards.
   * @return - whether all card cells in this grid are filled with cards
   */
  public boolean allCellsFilled() {
    for (AGridCell[] row : grid) {
      for (AGridCell cell : row) {
        if (!cell.hasCard()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * The total number of red cards in this grid.
   * @return - the total number of red cards in this grid.
   */
  public int totalRedCards() {
    int redCount = 0;
    for (AGridCell[] row : grid) {
      for (AGridCell cell: row) {
        if (cell.hasCard() && cell.getCard().getCoach().getName().equals("red")) {
          redCount += 1;
        }
      }
    }
    return redCount;
  }

  /**
   * The total number of blue cards in this grid.
   * @return - the total number of blue cards in this grid.
   */
  public int totalBlueCards() {
    int blueCount = 0;
    for (AGridCell[] row : grid) {
      for (AGridCell cell : row) {
        if (cell.hasCard() && cell.getCard().getCoach().getName().equals("blue")) {
          blueCount += 1;
        }
      }
    }
    return blueCount;
  }

  /**
   * The total number of cards in this grid.
   * @return - the total number of card cells in this grid.
   */
  public int getTotalCardCells() {
    int cellCount = 0;
    for (AGridCell[] row : grid) {
      for (AGridCell cell : row) {
        if (!cell.hasCard()) {
          cellCount += 1;
        }
      }
    }
    return cellCount;
  }

  /**
   * The grid of this.
   * @return - the grid of this.
   */
  public AGridCell[][] getGrid() {
    return this.grid;
  }
}
