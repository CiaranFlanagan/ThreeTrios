package cs3500.threetrios.model;

/**
 * to represent a cell that can hold no cards in three trios.
 */
public final class GridCellHole extends GridCellAbstract {

  /**
   * constructor.
   */
  public GridCellHole() {
    super();
  }

  @Override
  protected String renderTextConstructor() {
    return "X";
  }



  @Override
  protected void acceptBattlePhase(IReferee battlePhase) {
    battlePhase.refereeBattlePhase(this);
  }
}
