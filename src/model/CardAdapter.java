package model;

import provider.COLOR;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapts provider card into a usable card class through a lens. Composition is used.
 */
public class CardAdapter implements Lens<Card, provider.Card>,
    provider.Card {

  private Card card;

  private CardAdapter(Card card) {
    this.card = card;
  }

  public CardAdapter() {
    //Public default constructor only.
  }

  @Override
  public void switchColor(COLOR color) {
    card.setCoach(new CoachLens().backward(color));
  }

  @Override
  public COLOR getColor() {
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
  public provider.Card forward(Card card) {
    return new CardAdapter(card);
  }

  @Override
  public Card backward(provider.Card card) {
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
