package cs3500.threetrios.model;

import java.util.EnumMap;
import java.util.Map;

public abstract class ABoardCell {
  private final Map<CardinalDirection, ABoardCell> neighbors;

  public ABoardCell() {
    this.neighbors = new EnumMap<>(CardinalDirection.class);
  }

  /**
   * to link [this] board cell to [other] in direction [dir]
   * EFFECT: also links [other] to [this] in the opposite direction of [dir]
   *
   * @param other - the other board cell
   * @param dir   - the direction [this] links to [other]
   */
  protected final void link(ABoardCell other, CardinalDirection dir) {
    this.neighbors.put(dir, other);
    other.neighbors.put(dir.opposite(), this);
  }

  ABoardCell getNeighborToThe(CardinalDirection direction) {
    return this.neighbors.get(direction);
  }

  protected boolean hasCard() {
    return false;
  }


  protected Card getCard() {
    throw new IllegalStateException("Can't get card from hole cell");
  }

  protected void placeCard(Card card) {
    throw new IllegalStateException("Can't place card on hole cell");
  }

  protected abstract void acceptBattlePhase(BattlePhaseReferee battlePhase);

  protected String renderTextConstructor() {
    throw new IllegalStateException("not implemented");
  }
}
