package cs3500.threetrios.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * A class to hold the paths to the files needed for test.
 */
public class TestFiles {
  public static Scanner GRID_ASSN_HARD = fromPath("grid_assn_hard");
  public static Scanner GRID_NO_HOLES = fromPath("grid_no_holes");
  public static Scanner GRID_CONNECTED_HOLES = fromPath("grid_connected");
  public static Scanner GRID_DISC_HOLES = fromPath("grid_disconnected");
  public static Scanner GRID_NO_HOLES_THREE_BY_THREE = fromPath("grid_no_holes_3by3");
  public static Scanner CC_SMALL = fromPath("cards_small");
  public static Scanner CC_LARGE = fromPath("cards_large");

  private static Scanner fromPath(String fileNameOnly) {
    String prepend = "./resources/";
    String resourceDirPath = "cs3500/threetrios/controller/";
    String path = resourceDirPath + fileNameOnly + ".txt";
    URL url = ClassLoader.getSystemResource(path);
    try {
      return new Scanner(url.openStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
