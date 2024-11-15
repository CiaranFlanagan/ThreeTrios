package cs3500.threetrios.model;

/**
 * to represent a cardinal direction.
 */
public enum CardinalDirection {
  NORTH,
  SOUTH,
  EAST,
  WEST;

  /**
   * to return the opposite direction of this.
   *
   * @return - the opposite direction of this.
   */
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