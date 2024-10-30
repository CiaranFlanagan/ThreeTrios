package cs3500.threetrios.model;

class BoardCellMockTestLinks extends ABoardCell {
  BoardCellMockTestLinks() {

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
