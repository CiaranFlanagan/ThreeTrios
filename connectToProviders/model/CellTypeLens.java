package model;

public class CellTypeLens implements Lens<CellType, provider.game.Cell.CellType> {

  @Override
  public provider.game.Cell.CellType forward(model.CellType cellType) {
    switch (cellType) {
      case CARD: return provider.game.Cell.CellType.CARD_CELL;
      case HOLE: return provider.game.Cell.CellType.HOLE;
      default: return null;
    }
  }

  @Override
  public model.CellType backward(provider.game.Cell.CellType cellType) {
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
