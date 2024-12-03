package model;

import java.util.function.Function;

public class CardAdapter implements Function<Card, provider.card.Card>,
    provider.card.Card {

  private Card card;

  @Override
  public provider.card.Card apply(Card card) {
    CardAdapter newCard = new CardAdapter();
    newCard.card = card;
    return newCard;
  }

  @Override
  public void switchColor(provider.card.COLOR color) {
    card.setCoach(new CoachAdapter().backward(color));
  }

  @Override
  public provider.card.COLOR getColor() {
    return new CoachAdapter().forward(card.getCoach());
  }

  @Override
  public int getSouth() {
    return this.card.getAttackValue(CardinalDirection.SOUTH).fromAttackValue();
  }

  @Override
  public int getNorth() {
    return this.card.getAttackValue(CardinalDirection.NORTH).fromAttackValue();
  }

  @Override
  public int getEast() {
    return this.card.getAttackValue(CardinalDirection.EAST).fromAttackValue();
  }

  @Override
  public int getWest() {
    return this.card.getAttackValue(CardinalDirection.WEST).fromAttackValue();
  }

  @Override
  public String getName() {
    return card.getName();
  }

}
