package cs3500.threetrios.model.done;

public enum CardinalDirection {
  NORTH, SOUTH, EAST, WEST;

public CardinalDirection opposite() {
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