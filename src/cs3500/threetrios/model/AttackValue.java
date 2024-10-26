package cs3500.threetrios.model;

/**
 * Represents the possible attack values for a card.
 */
public enum AttackValue {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  A(10);

  private final int numericValue;

  AttackValue(int numericValue) {
    this.numericValue = numericValue;
  }

  /**
   * Gets the numeric value of the attack.
   *
   * @return the numeric value
   */
  public int getNumericValue() {
    return numericValue;
  }

  @Override
  public String toString() {
    if (this == A) {
      return "A";
    }
    return String.valueOf(numericValue);
  }

  //This is for the file reader

  /**
   * Parses a string into an AttackValue.
   *
   * @param s the string to parse
   * @return the corresponding AttackValue
   * @throws IllegalArgumentException if the string does not represent a valid attack value
   */
public static AttackValue fromString(String s) {
  switch (s.toUpperCase()) {
    case "1":
      return ONE;
    case "2":
      return TWO;
    case "3":
      return THREE;
    case "4":
      return FOUR;
    case "5":
      return FIVE;
    case "6":
      return SIX;
    case "7":
      return SEVEN;
    case "8":
      return EIGHT;
    case "9":
      return NINE;
    case "A":
      return A;
    default:
      throw new IllegalArgumentException("Invalid attack value: " + s);
  }
}
}
