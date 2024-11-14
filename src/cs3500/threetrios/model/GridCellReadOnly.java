package cs3500.threetrios.model;

/**
 * represents a read only grid cell.
 */
public interface GridCellReadOnly {
  /**
   * To evaluate if this cell can have a card.
   * @return - if this cell can have a card
   */
  boolean canHaveCard();

  /**
   * Whether this cell has a card.
   *
   * @return - whether this cell has a card
   */
  boolean hasCard();

  /**
   * To return the card held in this cell.
   *
   * @return - the card held in this cell.
   */
  Card getCard();

  /**
   * evaluates if this cell has a neighbor to the [direction].
   * @param direction - the direction
   * @return - if cell has a neighbor in direction
   */
  boolean hasNeighborToThe(CardinalDirection direction);

  /**
   * To get the neighbor to the [N/S/E/W].
   *
   * @param direction - the cardinal direction to consider
   * @return - the neighbor (if any) in [direction]
   */
  GridCellReadOnly getNeighborToThe(CardinalDirection direction);

}
