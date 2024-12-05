package model;

import java.util.HashMap;
import java.util.Map;

public class CardAdapter implements Lens<Card, provider.card.Card>,
    provider.card.Card {

  private Card card;

  private CardAdapter(Card card) {
    this.card = card;
  }

  public CardAdapter() {

  }

  @Override
  public void switchColor(provider.card.COLOR color) {
    card.setCoach(new CoachLens().backward(color));
  }

  @Override
  public provider.card.COLOR getColor() {
    return new CoachLens().forward(card.getCoach());
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

  @Override
  public provider.card.Card forward(Card card) {
    return new CardAdapter(card);
  }

  @Override
  public Card backward(provider.card.Card card) {
    Map<CardinalDirection, AttackValue> map = new HashMap<>();
    map.put(CardinalDirection.NORTH, AttackValue.fromString(Integer.toHexString(
        card.getNorth())));
    map.put(CardinalDirection.SOUTH, AttackValue.fromString(Integer.toHexString(
        card.getSouth())));
    map.put(CardinalDirection.EAST, AttackValue.fromString(Integer.toHexString(
        card.getEast())));
    map.put(CardinalDirection.WEST, AttackValue.fromString(Integer.toHexString(
        card.getWest())));
    return new Card(card.getName(), map);
  }

}
