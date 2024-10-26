package cs3500.threetrios.model;

import java.util.List;

public class TTGrid implements BoardGrid{

  private final int rows;
  private final int cols;
  private final BoardCell[][] cells;

  public TTGrid(int rows, int cols, CellType[][] cellTypes) {
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("Grid dimensions must be positive");
    }
    if (cellTypes == null || cellTypes.length != rows) {
      throw new IllegalArgumentException("Invalid cell types array");
    }
    this.rows = rows;
    this.cols = cols;
    this.cells = new BoardCell[rows][cols];

    initializeCells(cellTypes);
    setNeighbors();
  }

  private void setNeighbors() {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        BoardCell cell = cells[row][col];
        for (CardinalDirection dir : CardinalDirection.values()) {
          int neighborRow = row + dir.getRowOffset();
          int neighborCol = col + dir.getColOffset();
          if (isValidPosition(neighborRow, neighborCol)) {
            BoardCell neighbor = cells[neighborRow][neighborCol];
            cell.setNeighbor(dir, neighbor);
          }
        }
      }
    }
  }


  private void initializeCells(CellType[][] cellTypes) {
    for (int row = 0; row < rows; row++) {
      if (cellTypes[row].length != cols) {
        throw new IllegalArgumentException("Invalid cell types array at row " + row);
      }
      for (int col = 0; col < cols; col++) {
        Position position = new Position(row, col);
        if (cellTypes[row][col] == CellType.CARD_CELL) {
          cells[row][col] = new CardCell(position);
        } else if (cellTypes[row][col] == CellType.HOLE) {
          cells[row][col] = new HoleCell(position);
        } else {
          throw new IllegalArgumentException("Unknown cell type at (" + row + ", " + col + ")");
        }
      }
    }
  }
  private boolean isValidPosition(int row, int col) {
    return row >= 0 && row < rows && col >= 0 && col < cols;
  }


  /**
   * @return
   */
  @Override
  public int getRows() {
    return 0;
  }

  /**
   * @return
   */
  @Override
  public int getColumns() {
    return 0;
  }

  /**
   * @param row
   * @param col
   * @return
   */
  @Override
  public BoardCell getCell(int row, int col) {
    return null;
  }

  /**
   * @return
   */
  @Override
  public List<BoardCell> getAllCells() {
    return null;
  }
}
