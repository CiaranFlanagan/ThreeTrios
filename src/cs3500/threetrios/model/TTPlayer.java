package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

public class TTPlayer implements GamePlayer {
  private final String name;
  private final List<Card> hand;

  /**
   * Constructs a Player with the given name.
   *
   * @param name the player's name
   */
  public TTPlayer(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Player name cannot be null or empty");
    }
    this.name = name;
    this.hand = new ArrayList<>();
  }


  /**
   * Gets the name of the player.
   *
   * @return the player's name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the player's hand of cards.
   *
   * @return the player's hand
   */
  @Override
  public List<Card> getHand() {
    return hand;
  }

  /**
   * Removes a card from the player's hand.
   *
   * @param card the card to remove
   */
  @Override
  public void removeCard(Card card) {
    if (!hand.remove(card)) {
      throw new IllegalArgumentException("Card not found in player's hand");
    }
  }

  /**
   * Adds a card to the player's hand.
   *
   * @param card the card to add
   */
  @Override
  public void addCard(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null");
    }
    hand.add(card);
  }
}
