package cs3500.threetrios.model;

public class GridBoard {
  private final ABoardCell[][] grid; // at least 2x2
  public GridBoard (ABoardCell[][] grid) {
    this.grid = grid;
    linkRows();
    linkCols();
  }

  // to link the cells in each rows horizontally
  private void linkRows() {
    for (ABoardCell[] row : grid) {
      for (int curCol = 1; curCol < grid[0].length; curCol++) {
        row[curCol].link(row[curCol - 1], CardinalDirection.WEST);
      }
    }
  }

  private void linkCols() {
    for (int curRow = 1; curRow < grid.length; curRow++) {
      for (int curCol = 0; curCol < grid[0].length; curCol++) {
        grid[curRow][curCol].link(grid[curRow - 1][curCol], CardinalDirection.NORTH);
      }

    }
  }

  @Override
  public String toString() {
    String out = grid.length + " " + grid[0].length + "\n";
    for (ABoardCell[] row : grid) {
      for (ABoardCell cell : row) {
        out += cell.renderTextConstructor();
      }
      out += "\n";
    }
    return out;
  }
}
