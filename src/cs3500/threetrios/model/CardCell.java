package cs3500.threetrios.model;

public class CardCell extends ABoardCell {
  private Card card;

  public CardCell() {

  }

  @Override
  protected boolean hasCard() {
    return this.card != null;
  }

  @Override
  protected Card getCard() {
    return this.card;
  }

  @Override
  protected void placeCard(Card card) {
    if (this.card != null) {
      throw new IllegalStateException("can't place card on card cell twice");
    } else {
      this.card = card;
    }
  }


  protected void acceptBattlePhase(BattlePhaseReferee battlePhase) {
    battlePhase.refereeBattlePhase(this);
  }


  @Override
  protected String renderTextConstructor() {
    return "C";
  }

}
