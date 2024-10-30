package cs3500.threetrios.model;

/**
 * to represent a cell that can hold no cards in three trios.
 */
public class HoleCell extends AGridCell {

  /**
   * constructor.
   */
  public HoleCell() {
    super();
  }

  @Override
  protected String renderTextConstructor() {
    return "X";
  }



  @Override
  protected void acceptBattlePhase(BattlePhaseReferee battlePhase) {
    battlePhase.refereeBattlePhase(this);
  }
}
