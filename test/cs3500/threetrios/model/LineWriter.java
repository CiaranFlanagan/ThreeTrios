package cs3500.threetrios.model;

/**
 * Represents a player in the game.
 */
public class LineWriter {
  StringBuilder sb;

  private LineWriter() {
    sb = new StringBuilder();
  }
  public static LineWriter create() {
    return new LineWriter();
  }

  public LineWriter line(String line) {
    sb.append(line).append("\n");
    return this;
  }

  public LineWriter endWith(String s) {
    sb.append(s);
    return this;
  }

  @Override
  public String toString() {
    return sb.toString();
  }
}