package cs3500.threetrios.model;

class BoardCellMockTestLinks extends ABoardCell {
  BoardCellMockTestLinks() {

  }

  @Override
  public boolean hasCard() {
    return false;
  }

  public boolean hasNeighborIn(CardinalDirection dir) {
    return getNeighborToThe(dir) != null;
  }

}
