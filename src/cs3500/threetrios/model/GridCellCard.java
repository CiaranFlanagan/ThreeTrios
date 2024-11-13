package cs3500.threetrios.model;

/**
 * To represent a cell that can hold a card on a grid in three trios.
 */
public final class GridCellCard extends GridCellAbstract {
  private Card card;

  /**
   * constructor.
   */
  public GridCellCard() {
    super();
    this.card = null;
  }

  @Override
  public boolean canHaveCard() {
    return true;
  }

  @Override
  public boolean hasCard() {
    return this.card != null;
  }

  @Override
  public Card getCard() {
    return this.card.copy();
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
  public void acceptBattlePhase(Referee battlePhase) {
    battlePhase.refereeBattlePhase(this);
  }


  @Override
  protected String renderTextConstructor() {
    return "C";
  }

}
