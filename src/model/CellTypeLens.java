package model;

import provider.Cell;

public class CellTypeLens implements Lens<CellType, Cell.CellType> {

  @Override
  public Cell.CellType forward(model.CellType cellType) {
    switch (cellType) {
      case CARD: return Cell.CellType.CARD_CELL;
      case HOLE: return Cell.CellType.HOLE;
      default: return null;
    }
  }

  @Override
  public model.CellType backward(Cell.CellType cellType) {
    switch (cellType) {
      case CARD_CELL:
        return model.CellType.CARD;
      case HOLE:
        return model.CellType.HOLE;
      default:
        return null;
    }
  }

}
