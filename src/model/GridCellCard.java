package model;

/**
 * Represents a cell on the grid that can hold a card in the Three Trios game. A
 * GridCellCard can accept and hold one card at a time.
 */
public final class GridCellCard extends GridCellAbstract {

  private Card card;

  /**
   * Constructor.
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
    return this.card;
  }


  @Override
  protected void placeCard(Card card) {
    if (this.card != null) {
      throw new IllegalStateException("can't place card on card cell twice\ncurrent "
                                          + "card: " + this.card);
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
