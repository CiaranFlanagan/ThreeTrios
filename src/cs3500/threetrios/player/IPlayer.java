package cs3500.threetrios.player;

import cs3500.threetrios.model.Card;

import java.util.List;

/**
 * Represents a player in the game.
 */
public interface IPlayer {
  /**
   * returns the name of the player.
   * @throws IllegalStateException if not initialized
   * @return the player's name
   */
  String getName();


  /**
   * returns the player's hand of cards.
   * @throws IllegalStateException if not initialized
   * @return the player's hand
   */
  List<Card> getHand();

  /**
   * removes a card from the player's hand.
   * @throws IllegalStateException if not initialized
   * @param card the card to remove
   */
  void removeCard(Card card);

  /**
   * Initializes a player with given [hand].
   * @param name - the name of the player
   * @param hand - the hand of cards
   */
  void init(String name, List<Card> hand);
}

