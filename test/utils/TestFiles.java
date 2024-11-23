package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * A class to hold the paths to the files needed for test.
 */
public class TestFiles {

  public static Supplier<Scanner> GRID_ASSN_HARD = fromPath("grid_assn_hard");
  public static Supplier<Scanner> GRID_NO_HOLES = fromPath("grid_no_holes");
  public static Supplier<Scanner> GRID_CONNECTED_HOLES = fromPath("grid_connected");
  public static Supplier<Scanner> GRID_DISC_HOLES = fromPath("grid_disconnected");
  public static Supplier<Scanner> GRID_NO_HOLES_THREE_BY_THREE =
      fromPath("grid_no_holes_3by3");
  public static Supplier<Scanner> CC_SMALL = fromPath("cards_small");
  public static Supplier<Scanner> CC_LARGE = fromPath("cards_large");

  private static Supplier<Scanner> fromPath(String fileNameOnly) {
    String resourceDirPath = "controller/";
    String path = resourceDirPath + fileNameOnly + ".txt";
    URL url = ClassLoader.getSystemResource(path);
    return () -> {
      try {
        InputStream inputStream = url.openStream();
        return new Scanner(inputStream);
      } catch (IOException ex) {
        throw new IllegalStateException("bad path or error in finding file");
      }
    };
  }

}
