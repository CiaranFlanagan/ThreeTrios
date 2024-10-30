package cs3500.threetrios.model;

/**
 * to represent a cell that can hold a card on a grid in three trios.
 */
public class CardCell extends AGridCell {
  private Card card;

  /**
   * constructor.
   */
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

  @Override
  protected void acceptBattlePhase(BattlePhaseReferee battlePhase) {
    battlePhase.refereeBattlePhase(this);
  }


  @Override
  protected String renderTextConstructor() {
    return "C";
  }

}
