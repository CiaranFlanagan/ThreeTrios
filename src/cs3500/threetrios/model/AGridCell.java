package cs3500.threetrios.model;

import java.util.EnumMap;
import java.util.Map;

/**
 * To represent a cell on a grid on a game of three trios.
 */
public abstract class AGridCell {
  private final Map<CardinalDirection, AGridCell> neighbors;

  /**
   * constructor.
   */
  public AGridCell() {
    this.neighbors = new EnumMap<>(CardinalDirection.class);
  }

  /**
   * to link [this] board cell to [other] in direction [dir]
   * EFFECT: also links [other] to [this] in the opposite direction of [dir]
   *
   * @param other - the other board cell
   * @param dir   - the direction [this] links to [other]
   */
  protected final void link(AGridCell other, CardinalDirection dir) {
    this.neighbors.put(dir, other);
    other.neighbors.put(dir.opposite(), this);
  }

  /**
   * to get the neighbor to the [N/S/E/W].
   * @param direction - the cardinal direction to consider
   * @return - the neighbor (if any) in [direction]
   */
  AGridCell getNeighborToThe(CardinalDirection direction) {
    return this.neighbors.get(direction);
  }

  /**
   * whether this cell has a card.
   * @return - whether this cell has a card
   */
  protected boolean hasCard() {
    return false;
  }

  /**
   * to return the card held in this cell.
   * @return - the card held in this cell.
   */
  protected Card getCard() {
    throw new IllegalStateException("Can't get card from hole cell");
  }

  /**
   * to place the card on this cell.
   * @param card - the card to place
   */
  protected void placeCard(Card card) {
    throw new IllegalStateException("Can't place card on hole cell");
  }

  /**
   * to accept a battle phase officiated by [ref]
   * @param ref - the ref that controls the rules and what happens during this battle phase
   */
  protected abstract void acceptBattlePhase(BattlePhaseReferee ref);

  protected String renderTextConstructor() {
    throw new IllegalStateException("not implemented");
  }
}
