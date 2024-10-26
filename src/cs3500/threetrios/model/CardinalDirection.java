package cs3500.threetrios.model;

public enum CardinalDirection implements Direction {
  NORTH(-1, 0),
  EAST(0, 1),
  SOUTH(1, 0),
  WEST(0, -1);

  private final int rowOffset;
  private final int colOffset;

  CardinalDirection(int rowOffset, int colOffset) {
    this.rowOffset = rowOffset;
    this.colOffset = colOffset;
  }

  public int getRowOffset() {
    return rowOffset;
  }

  public int getColOffset() {
    return colOffset;
  }

public Direction opposite() {
  switch (this) {
    case NORTH:
      return SOUTH;
    case EAST:
      return WEST;
    case SOUTH:
      return NORTH;
    case WEST:
      return EAST;
    default:
      throw new IllegalArgumentException("Invalid direction");
  }
}


}
