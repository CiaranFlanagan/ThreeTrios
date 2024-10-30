package cs3500.threetrios.utils;

import java.io.File;
import java.io.FileWriter;

/**
 * Represents a player in the game.
 */
public class LineWriter {
  StringBuilder sb;

  public LineWriter() {
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