package cs3500.threetrios.model;

/**
 * Represents the grid-shaped board of the Three Trios game.
 * Manages cells, tracks holes, and provides methods to query and modify the grid.
 */
public final class Grid {
  private final GridCellAbstract[][] internalArray;
  private final int NUM_ROWS;
  private final int NUM_COLS;
  private final int numHoles;
  private CellType[][] shape;

  /**
   * Constructs a Grid with a specified shape.
   * Initializes each cell based on the provided shape's cell type.
   *
   * @param shape the grid configuration as a 2D array of CellType
   * @throws IllegalArgumentException if shape is null or has invalid dimensions
   */
  public Grid(CellType[][] shape) {
    this.shape = shape;
    if (shape == null) {
      throw new IllegalArgumentException();
    }
    if (shape.length < 1 || shape[0].length < 1) {
      throw new IllegalArgumentException("Rows and Cols must be positive integers");
    }
    this.NUM_ROWS = shape.length;
    this.NUM_COLS = shape[0].length;
    int numHoles = 0;
    GridCellAbstract[][] privGrid = new GridCellAbstract[NUM_ROWS][NUM_COLS];
    for (int row = 0; row < NUM_ROWS; row++) {
      for (int col = 0; col < NUM_COLS; col++) {
        CellType curCellType = shape[row][col];
        if (curCellType == CellType.HOLE) {
          numHoles += 1;
          privGrid[row][col] = new GridCellHole();
        } else {
          privGrid[row][col] = new GridCellCard();
        }
      }
    }
    this.numHoles = numHoles;
    this.internalArray = privGrid;
    linkRows();
    linkCols();
  }

  @Override
  public String toString() {
    String out = internalArray.length + " " + internalArray[0].length + "\n";
    for (GridCellAbstract[] row : (GridCellAbstract[][]) internalArray) {
      for (GridCellAbstract cell : row) {
        out += cell.renderTextConstructor();
      }
      out += "\n";
    }
    return out.stripTrailing();
  }

  /**
   * Checks if all cells in the grid are filled with cards.
   *
   * @return true if the grid is fully occupied, false otherwise
   */
  public boolean isFull() {
    for (GridCellReadOnly[] row : internalArray) {
      for (GridCellReadOnly cell : row) {
        if (!cell.hasCard()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Gets the number of holes in the grid.
   *
   * @return the number of holes
   */
  public int getNumHoles() {
    return this.numHoles;
  }

  /**
   * Provides a read-only view of the grid as a 2D array.
   *
   * @return a 2D array of GridCellReadOnly
   */
  public GridCellReadOnly[][] readOnlyArray2D() {
    GridCellReadOnly[][] readOnlyGrid = new GridCellReadOnly[NUM_ROWS][NUM_COLS];
    for (int row = 0; row < NUM_ROWS; row += 1) {
      for (int col = 0; col < NUM_COLS; col += 1) {
        readOnlyGrid[row][col] = new GridCellReadOnlyImpl(internalArray[row][col]);
      }
    }
    return readOnlyGrid;
  }

  // to link the cells in each row horizontally
  private void linkRows() {
    for (GridCellAbstract[] row : internalArray) {
      for (int curCol = 1; curCol < NUM_COLS; curCol++) {
        row[curCol].link(row[curCol - 1], CardinalDirection.WEST);
      }
    }
  }

  // to link the cells in each column vertically
  private void linkCols() {
    for (int curRow = 1; curRow < NUM_ROWS; curRow++) {
      for (int curCol = 0; curCol < NUM_COLS; curCol++) {
        internalArray[curRow][curCol].link(internalArray[curRow - 1][curCol],
                                           CardinalDirection.NORTH);
      }
    }
  }

  // to place [card] at [row], [col] on this
  // index at 0
  GridCellAbstract placeCardOn(int row, int col, Card card) {
    if (row < 0 || col < 0 || row >= internalArray.length || col >= internalArray[0].length) {
      throw new IllegalArgumentException("Rows and Cols must be positive integers");
    }
    GridCellAbstract relevantCell = internalArray[row][col];
    relevantCell.placeCard(card);
    return relevantCell;
  }

  Grid copy() {
    Grid copy = new Grid(shape);
    for (int row = 0; row < NUM_ROWS; row += 1) {
      for (int col = 0; col < NUM_COLS; col += 1) {
        GridCellAbstract ourCell = internalArray[row][col];
        if (ourCell.hasCard()) {
          copy.placeCardOn(row, col, ourCell.getCard().copy());
        }
      }
    }
    return copy;
  }


}
