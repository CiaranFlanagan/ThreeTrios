package cs3500.threetrios.model;

class GridCellReadOnlyImpl implements GridCellReadOnly {
  GridCellAbstract delegate;
  GridCellReadOnlyImpl(GridCellAbstract gridCellAbstract) {
    delegate = gridCellAbstract;
  }

  @Override
  public boolean canHaveCard() {
    return delegate.canHaveCard();
  }

  @Override
  public boolean hasCard() {
    return delegate.hasCard();
  }

  @Override
  public Card getCard() {
    return delegate.getCard().copy();
  }

  @Override
  public boolean hasNeighborToThe(CardinalDirection direction) {
    return delegate.hasNeighborToThe(direction);
  }

  @Override
  public GridCellReadOnly getNeighborToThe(CardinalDirection direction) {
    return delegate.getNeighborToThe(direction);
  }
}
