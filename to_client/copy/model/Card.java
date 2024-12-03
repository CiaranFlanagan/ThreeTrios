package copy.model;


import java.util.Collections;

/**
 * to represent a card in three trios.
 */
public interface Card {


  /**
   * to return the name of the card.
   * @return - the name of the card.
   */
  String getName();

  /**
   * to return the coach of this card.
   * @return - the current coach of this card.
   */
  CoachColor getCoach();

  /**
   * to evaluate if this card beats [other] in [direction].
   * @param other     - the card to compare with
   * @param direction - the direction where this battles other
   * @return the result of the battle.
   */
  boolean beats(Card other, CardinalDirection direction);

  /**
   * to return the attack value of this card in [direction].
   * @param direction - the direction to check
   * @return - the attack value in [direction]
   */
  AttackValue getAttackValue(CardinalDirection direction);

}
