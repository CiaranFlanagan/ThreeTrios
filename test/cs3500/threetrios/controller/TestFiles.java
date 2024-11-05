package cs3500.threetrios.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class to hold the paths to the files needed for test.
 */
public class TestFiles {
  public static File GRID_ASSN_HARD = new File("./test/cs3500/threetrios/controller/" +
          "grid_assn_hard.txt");
  public static File GRID_NO_HOLES = new File("./test/cs3500/threetrios/controller/" +
          "grid_no_holes.txt");
  public static File GRID_CONNECTED_HOLES = new File(
          "./test/cs3500/threetrios/controller/" +
          "grid_connected.txt");
  public static File GRID_DISC_HOLES = new File(
          "./test/cs3500/threetrios/controller/" +
                  "grid_disconnected.txt");
  public static File CC_SMALL = new File("./test/cs3500/threetrios/controller/" +
          "cards_small.txt");
  public static File CC_LARGE = new File(
          "./test/cs3500/threetrios/controller/cards_large.txt");


  /**
   * Converts a file to a scanner.
   *
   * @param f the file
   * @return the scanner
   */
  public static Scanner safeFileToScanner(File f) {
    try {
      return new Scanner(f);
    } catch (FileNotFoundException ex) {
      throw new RuntimeException("file not found, check TestFiles");
    }
  }
}
