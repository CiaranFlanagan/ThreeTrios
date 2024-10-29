package cs3500.threetrios.model.done;

import cs3500.threetrios.model.IPlayer;

public class CardCell extends ABoardCell {
  private TTCard card;
  private IPlayer owner;

  public CardCell() {
  }



  // Specific methods for CardCell
  public void placeCard(TTCard card, IPlayer owner) {
    if (this.card != null) {
      throw new IllegalStateException("Cell already has a card");
    }
    if (card == null || owner == null) {
      throw new IllegalArgumentException("Card and owner cannot be null");
    }
    this.card = card;
    this.owner = owner;
  }

  public TTCard getCard() {
    return card;
  }

  public IPlayer getOwner() {
    return owner;
  }

  public void battleAllPossible() {
    if (card == null) {
      throw new IllegalStateException("no card");
    }
    for (CardinalDirection direction : neighbors.keySet()) {
      ABoardCell neighbor = neighbors.get(direction);
      neighbor.battleAndCascadeOnLose(card, direction.opposite(), this.owner);
    }
  }


  public void battleAndCascadeOnLose(TTCard otherCard, CardinalDirection direction, IPlayer maybeNewOwner) {
    // card hasn't been played to this cell
    if (card == null) {
      return;
    }

    // card has same owner as other
    else if (owner == maybeNewOwner) {
      return;
    }

    else if (otherCard.beats(this.card, direction)) {
      this.owner = maybeNewOwner;
      battleAllPossible();
    }
  }

  @Override
  public String toString() {
    return "C";
  }
}
