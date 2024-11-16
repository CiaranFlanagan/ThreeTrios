package model;

/**
 * Represents a grid cell that can be interacted with during the battle phase of the Three Trios
 * game. Extends {@link GridCellReadOnly} to add functionality for accepting a {@link Referee}.
 */
public interface GridCellVisitable extends GridCellReadOnly {

  /**
   * Allows a {@link Referee} to interact with this cell during the battle phase.
   *
   * @param ref the referee handling the battle phase
   */
  void acceptBattlePhase(Referee ref);

}
