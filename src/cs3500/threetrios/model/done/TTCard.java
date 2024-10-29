package cs3500.threetrios.model.done;

import java.util.HashMap;
import java.util.Map;

public class TTCard {

  private final String name;
  private final Map<CardinalDirection, AttackValue> attackValues;

  /**
   *
   * @param name - name of this card
   * @param attackValues - attack value in each direction
   */
  public TTCard(String name, Map<CardinalDirection, AttackValue> attackValues) {
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

  // info needed to battle
  public boolean beats(TTCard other, CardinalDirection direction) {
    return attackValues.get(direction).fromAttackValue()
            > other.attackValues.get(direction.opposite()).fromAttackValue();
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
