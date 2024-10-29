package cs3500.threetrios.model.done;

import cs3500.threetrios.model.IPlayer;

import java.util.EnumMap;
import java.util.Map;

public abstract class ABoardCell {
  protected final Map<CardinalDirection, ABoardCell> neighbors;

  public ABoardCell() {
    this.neighbors = new EnumMap<>(CardinalDirection.class);
  }

  /**
   * to link [this] board cell to [other] in direction [dir]
   * EFFECT: also links [other] to [this] in the opposite direction of [dir]
   * @param other - the other board cell
   * @param dir - the direction [this] links to [other]
   */
  public final void link(ABoardCell other, CardinalDirection dir) {
    this.neighbors.put(dir, other);
    other.neighbors.put(dir.opposite(), this);
  }

  /**
   *
   */
  public abstract void battleAllPossible();

  /**
   *
   * @param card
   */
  public abstract void battleAndCascadeOnLose(TTCard card, CardinalDirection dir,
                                              IPlayer maybeNewOwner);
}
