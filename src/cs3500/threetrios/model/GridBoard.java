package cs3500.threetrios.model;

import cs3500.threetrios.model.done.ABoardCell;
import cs3500.threetrios.model.done.CardinalDirection;

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

  @Override
  public String toString() {
    String out = grid.length + " " + grid[0].length + "\n";
    for (ABoardCell[] row : grid) {
      for (ABoardCell cell : row) {
        out += cell.toString();
      }
      out += "\n";
    }
    return out;
  }
}
