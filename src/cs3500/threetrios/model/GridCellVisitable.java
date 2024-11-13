package cs3500.threetrios.model;

public interface GridCellVisitable extends GridCellReadOnly {
  void acceptBattlePhase(Referee ref);
}
