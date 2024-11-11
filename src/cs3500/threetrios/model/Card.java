package cs3500.threetrios.model;

import java.util.HashMap;
import java.util.Map;

/**
 * to represent a card in three trios.
 */
public final class Card {

  private final String name;
  private final Map<CardinalDirection, AttackValue> attackValues;
  private Coach.Color coachColor;

  /**
   * to construct a card with [name] and [attackValues].
   * @param name - name of this card
   * @param attackValues - attack value in each direction
   */
  public Card(String name, Map<CardinalDirection, AttackValue> attackValues) {
    if (name == null) {
      throw new IllegalArgumentException("card name cannot be null");
    }
    if (attackValues == null) {
      throw new IllegalArgumentException("attack values cannot be null");
    }
    if (name.isEmpty()) {
      throw new IllegalArgumentException("card name cannot be empty");
    }
    this.name = name;
    this.attackValues = new HashMap<>(attackValues);
  }


  // info needed to render the card

  /**
   * to return the name of the card.
   * @return - the name of the card.
   */
  public String getName() {
    return name;
  }

  /**
   * to return the attack value of this card in [direction].
   * @param direction - the direction to check
   * @return - the attack value in [direction]
   */
  public AttackValue getAttackValue(CardinalDirection direction) {
    return attackValues.get(direction);
  }

  /**
   * to return the coach of this card.
   * @return - the current coach of this card.
   */
  public Coach.Color getCoachColor() {
    return this.coachColor;
  }

  /**
   * to evaluate if this card beats [other] in [direction].
   * @param other - the card to compare with
   * @param direction - the direction where this battles other
   * @return the result of the battle.
   */
  public boolean beats(Card other, CardinalDirection direction) {
    return getAttackValue(direction).beats(other.getAttackValue(direction.opposite()));
  }



  @Override
  public String toString() {
    String s = name;
    for (CardinalDirection c : CardinalDirection.values()) {
      s += " " + attackValues.get(c).toString();
    }
    return s;
  }

  // package private to prevent excess side-effect

  /**
   * to set the attack value in [direction] to [av].
   * @param av - the attack value to add
   * @param direction - the direction to update
   */
  void setAttackValueInDirection(AttackValue av, CardinalDirection direction) {
    attackValues.put(direction, av);
  }

  /**
   * to update the coach to [newCoach].
   * @param newCoach - the new coach of this
   */
  void setCoachColor(Coach.Color newCoach) {
    this.coachColor = newCoach;
  }




}
