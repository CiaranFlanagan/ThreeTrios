package model;

import provider.Card;
import provider.Cell;

public class GridAdapter implements provider.Grid, Lens<Grid, provider.Grid> {

  private Grid grid;

  GridAdapter(Grid grid) {
    this.grid = grid;
  }

  public GridAdapter() {

  }

  @Override
  public provider.Grid forward(Grid grid) {
    return new model.GridAdapter(grid);
  }

  @Override
  public Grid backward(provider.Grid grid) {
    CellType[][] arr = new model.CellType[grid.getRows()][grid.getCols()];
    for (int row = 0; row < grid.getRows(); row += 1) {
      for (int col = 0; col < grid.getCols(); col += 1) {
        arr[row][col] = new CellTypeLens().backward(grid.getCellType(row, col));
      }
    }
    return new Grid(arr);
  }

  @Override
  public void placeCard(int row, int col, Card card) {
    grid.placeCardOn(row, col, new CardAdapter().backward(card));
  }

  @Override
  public Card getCard(int row, int col) {
    return new CardAdapter().forward(grid.readOnlyArray2D()[row][col].getCard());
  }

  @Override
  public boolean isEmpty(int row, int col) {
    return gridToCellStream()
        .noneMatch(model.GridCellReadOnly :: hasCard);
  }

  @Override
  public Cell.CellType getCellType(int row, int col) {
    return grid.readOnlyArray2D()[row][col].canHaveCard() ?
        Cell.CellType.CARD_CELL : Cell.CellType.HOLE;
  }

  @Override
  public int getRows() {
    return grid.numRows();
  }

  @Override
  public int getCols() {
    return grid.numCols();
  }

  @Override
  public boolean validPosition(int row, int col) {
    return row > -1 && row < getRows() && col > -1 && col < getCols();
  }

  @Override
  public int getNumCardsCells() {
    return (int) gridToCellStream()
        .filter(model.GridCellReadOnly :: canHaveCard)
        .count();
  }

  @Override
  public Cell[][] getCells() {
    return new Cell[0][];
  }

  @Override
  public void setCellType(int row, int col, Cell.CellType type) {
    // can't really do this, if they use mutation then we are cooked
  }

  private java.util.stream.Stream<GridCellReadOnly> gridToCellStream() {
    return java.util.Arrays.stream(grid.readOnlyArray2D())
                           .flatMap(java.util.Arrays :: stream);
  }

}
