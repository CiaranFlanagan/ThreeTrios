package cs3500.threetrios.model;


public class HoleCell extends ABoardCell {

  public HoleCell() {
    super();
  }

  @Override
  protected String renderTextConstructor() {
    return "X";
  }



  @Override
  protected void acceptBattlePhase(BattlePhaseReferee battlePhase) {
    battlePhase.refereeBattle(this);
  }
}
