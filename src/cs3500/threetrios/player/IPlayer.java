package cs3500.threetrios.player;

import cs3500.threetrios.model.Card;

import java.util.List;

/**
 * Represents a player in the game.
 */
public interface IPlayer {
  /**
   * returns the name of the player.
   * @return the player's name
   * @throws IllegalStateException if not initialized
   */
  String getName();


  /**
   * returns the player's hand of cards.
   * @return the player's hand
   * @throws IllegalStateException if not initialized
   */
  List<Card> getHand();

  /**
   * removes a card from the player's hand.
   * @param card the card to remove
   * @throws IllegalStateException if not initialized
   */
  void removeCard(Card card);

  /**
   * Initializes a player with given [hand].
   * @param name - the name of the player
   * @param hand - the hand of cards
   */
  void init(String name, List<Card> hand);
}

