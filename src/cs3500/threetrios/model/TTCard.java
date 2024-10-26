package cs3500.threetrios.model;

import java.util.HashMap;
import java.util.Map;

public class TTCard implements Card {

  private final String name;
  private final Map<Direction, AttackValue> attackValues;

  public TTCard(String name, Map<Direction, AttackValue> attackValues) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Card name cannot be null or empty");
    }
    if (attackValues == null || attackValues.size() != 4) {
      throw new IllegalArgumentException("Attack values must not be null and must include " +
              "all four directions");
    }
    this.name = name;
    this.attackValues = new HashMap<>(attackValues);
  }


  /**
   * @return
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * @param direction
   * @return
   */
  public AttackValue getAttackValue(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    return attackValues.get(direction);
  }
}
