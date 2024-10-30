package cs3500.threetrios.model;

import java.util.HashMap;
import java.util.Map;

public final class Card {

  private final String name;
  private final Map<CardinalDirection, AttackValue> attackValues;
  private Coach owner;

  /**
   *
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


  // info needed to render the card later

  public String getName() {
    return name;
  }

  public String getStringRepAttackValue(CardinalDirection direction, AttackValue av) {
    return av.toString();
  }

  void setAttackValueInDirection(AttackValue av, CardinalDirection direction) {
    attackValues.put(direction, av);
  }

  // info needed to battle
  public boolean beats(Card other, CardinalDirection direction) {
    return getAttackValue(direction).beats(other.getAttackValue(direction.opposite()));
  }

  public Coach getCoach() {
    return this.owner;
  }

  void setCoach(Coach newOwner) {
    this.owner = newOwner;
  }

  @Override
  public String toString() {
    String s = "<TTCard: " + name;
    for (CardinalDirection c : CardinalDirection.values()) {
      s += " " + attackValues.get(c).toString();
    }
    return s + ">";
  }
}
