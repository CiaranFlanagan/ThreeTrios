package cs3500.threetrios.model;

class GridCellMock extends GridCellAbstract {


  @Override
  public boolean hasCard() {
    return false;
  }

  @Override
  protected void acceptBattlePhase(IReferee battlePhase) {
    throw new IllegalStateException("not impl");
  }

  public boolean hasNeighborIn(CardinalDirection dir) {
    return getNeighborToThe(dir) != null;
  }

}
