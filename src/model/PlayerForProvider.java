package model;

import provider.card.COLOR;
import provider.card.Card;
import provider.player.Player;

import java.util.List;

public class PlayerForProvider implements Player {

  private String name;
  private List<Card> hand;
  private COLOR color;

  void setName(String name) {
    this.name = name;
  }

  void setHand(List<Card> hand) {
    this.hand = hand;
  }

  void setColor(COLOR color) {
    this.color = color;
  }

  @Override
  public String getName() {
    return name;
  }


  @Override
  public int handSize() {
    return hand.size();
  }

  @Override
  public List<Card> getHand() {
    return hand;
  }

  @Override
  public void removeCard(int index) {
    hand.remove(index);
  }

  @Override
  public void addCard(Card card) {
    hand.add(card);
  }

  @Override
  public Card getCard(int index) {
    return hand.get(index);
  }

  @Override
  public COLOR getColor() {
    return color;
  }

}
