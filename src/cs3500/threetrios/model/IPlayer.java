package cs3500.threetrios.model;

import cs3500.threetrios.model.done.TTCard;

import java.util.List;

/**
 * Represents a player in the game.
 */
public interface IPlayer {
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
  List<TTCard> getHand();

  /**
   * Removes a card from the player's hand.
   *
   * @param card the card to remove
   */
  void removeCard(TTCard card);

  /**
   * Adds a card to the player's hand.
   *
   * @param card the card to add
   */
  void addCard(TTCard card);
}

