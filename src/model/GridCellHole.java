package model;

/**
 * to represent a cell that can hold no cards in three trios.
 */
public final class GridCellHole extends GridCellAbstract {

  /**
   * Represents a hole in the grid of the Three Trios game. A GridCellHole cannot hold any
   * cards.
   */
  public GridCellHole() {
    super();
  }

  @Override
  public void acceptBattlePhase(Referee battlePhase) {
    battlePhase.refereeBattlePhase(this);
  }

  @Override
  protected String renderTextConstructor() {
    return "X";
  }

}
