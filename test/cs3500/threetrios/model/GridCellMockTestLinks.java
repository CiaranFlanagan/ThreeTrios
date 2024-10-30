package cs3500.threetrios.model;

class GridCellMockTestLinks extends AGridCell {
  GridCellMockTestLinks() {

  }

  @Override
  public boolean hasCard() {
    return false;
  }

  @Override
  protected void acceptBattlePhase(BattlePhaseReferee battlePhase) {
    throw new IllegalStateException("not impl");
  }

  public boolean hasNeighborIn(CardinalDirection dir) {
    return getNeighborToThe(dir) != null;
  }

}
