package cs3500.threetrios.model;

/**
 * Represents the possible attack values for a card. Construct once, immutable after construction.
 * unique keys and values that map to each other
 */
public enum AttackValue {
  ONE,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE,
  A;

  public static AttackValue fromString(String s) {
    switch (s) {
      case "1": return ONE;
      case "2": return TWO;
      case "3": return THREE;
      case "4": return FOUR;
      case "5": return FIVE;
      case "6": return SIX;
      case "7": return SEVEN;
      case "8": return EIGHT;
      case "9": return NINE;
      case "A": return A;
      default: throw new IllegalArgumentException("bad input. must be 1-9 or A; given: " + s);
    }
  }

  public int fromAttackValue() {
    switch (this) {
      case ONE: return 1;
      case TWO: return 2;
      case THREE: return 3;
      case FOUR: return 4;
      case FIVE: return 5;
      case SIX: return 6;
      case SEVEN: return 7;
      case EIGHT: return 8;
      case NINE: return 9;
      case A: return 10;
      default: throw new IllegalArgumentException("not valid attack");
    }
  }

  public boolean beats(AttackValue other) {
    return this.fromAttackValue() > other.fromAttackValue();
  }

  public String toString() {
    if (this == A) {
      return "A";
    }
    return String.valueOf(fromAttackValue());
  }

}
