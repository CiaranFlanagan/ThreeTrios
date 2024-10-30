package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Represents a player in the game.
 */
public class Coach {

  private final Color color;

  public enum Color {
    Red, Blue;

    @Override
    public String toString() {
      switch (this) {
        case Red:
          return "Red";
        case Blue:
          return "Blue";
        default:
          throw new IllegalArgumentException("Invalid color");
      }
    }
  }

  private final List<Card> hand;

  /**
   * Constructs a coach with the given color.
   *
   * @param color the player's color
   */
  public Coach(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null or empty");
    }
    this.color = color;
    this.hand = new ArrayList<>();
  }


  /**
   * Gets the name of the coach.
   *
   * @return the player's name
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Gets the coach's hand of cards.
   *
   * @return the player's hand
   */
  public List<Card> getHand() {
    return new ArrayList<>(hand);
  }

  /**
   *
   * @param idx - the index of the card to remove
   * @return the card that was removed
   */
  public Card removeCardFromHand(int idx) {
    if (idx >= hand.size()) {
      throw new IllegalArgumentException("Bad index");
    }
    return hand.remove(idx);
  }

  /**
   * Initializes a coach with given [hand].
   *
   * @param name - the name of the player
   * @param hand - the hand of cards
   */

  /**
   * Adds a card to the coach's hand.
   *
   * @param card the card to add
   */
  public void addCard(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null");
    }
    hand.add(card);
  }

  @Override
  public String toString() {
    return this.color.toString();
  }
}
