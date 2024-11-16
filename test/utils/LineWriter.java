package utils;

/**
 * Represents a player in the game.
 */
public class LineWriter {

  StringBuilder sb;

  /**
   * Constructs a LineWriter object.
   */
  public LineWriter() {
    sb = new StringBuilder();
  }

  /**
   * Creates a new LineWriter object.
   *
   * @return a new LineWriter object
   */
  public static LineWriter create() {
    return new LineWriter();
  }

  /**
   * Adds a line to the LineWriter object.
   *
   * @param line the line to add
   * @return the LineWriter object
   */
  public LineWriter line(String line) {
    sb.append(line).append("\n");
    return this;
  }

  /**
   * Adds a line to the LineWriter object x times.
   *
   * @param line the line to add
   * @param x the number of times to add the line
   * @return the LineWriter object
   */
  public LineWriter line(String line, int x) {
    for (int i = 0; i < x; i++) {
      sb.append(line).append("\n");
    }
    return this;
  }

  /**
   * Ends the LineWriter object with a string.
   *
   * @param s the string to end with
   * @return the LineWriter object
   */
  public LineWriter endWith(String s) {
    sb.append(s);
    return this;
  }

  @Override
  public String toString() {
    return sb.toString();
  }

}