package cs3500.threetrios.model;

/**
 * To represent an attack value of a card in a game of three trios.
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

  /**
   * to produce an attack value from a string, useful for IO.
   * @param s - the string.
   * @return - an attack value corresponding to the string.
   * @throws IllegalArgumentException - when input does not correspond to an AttackValue
   */
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

  /**
   * To convert an attack value to an integer.
   * @return - an integer corresponding the magnitude of attack value.
   */
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
      default: throw new IllegalArgumentException("invalid attack");
    }
  }

  /**
   * To evaluate whether this has a greater numeric attack value than other.
   * @param other - there other attack value to compare to
   * @return - whether this has a greater numeric attack value than other
   */
  public boolean beats(AttackValue other) {
    return this.fromAttackValue() > other.fromAttackValue();
  }

  @Override
  public String toString() {
    if (this == A) {
      return "A";
    }
    return String.valueOf(fromAttackValue());
  }

}
