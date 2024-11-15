package cs3500.threetrios.model;

/**
 * Represents a grid cell that can be visited during the battle phase of the Three Trios game.
 * This interface extends {@link GridCellReadOnly} to include functionality specific to
 * the battle phase, allowing a {@link Referee} to interact with the cell.
 */
public interface GridCellVisitable extends GridCellReadOnly {
  void acceptBattlePhase(Referee ref);
}
