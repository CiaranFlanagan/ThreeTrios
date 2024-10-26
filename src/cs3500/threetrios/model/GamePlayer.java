package cs3500.threetrios.model;

import java.util.List;

/**
 * Represents a player in the game.
 */
public interface GamePlayer {
  /**
   * Gets the name of the player.
   *
   * @return the player's name
   */
  String getName();

  /**
   * Gets the player's hand of cards.
   *
   * @return the player's hand
   */
  List<Card> getHand();

  /**
   * Removes a card from the player's hand.
   *
   * @param card the card to remove
   */
  void removeCard(Card card);

  /**
   * Adds a card to the player's hand.
   *
   * @param card the card to add
   */
  void addCard(Card card);
}

