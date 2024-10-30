package cs3500.threetrios.controller;

import cs3500.threetrios.model.AGridCell;
import cs3500.threetrios.model.CardCell;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.HoleCell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * To convert a file to a GridBoard.
 */
public class GridConfig {
  /**
   * to convert a file to a GridBoard.
   * @param f - the file
   * @return - a gridboard
   */
  public static Grid fileToGridBoard(File f) {
    if (f == null) {
      throw new IllegalArgumentException("null file");
    }
    FileReader fr;
    try {
      fr = new FileReader(f);
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException("file not found: " + f.toPath());
    }
    Scanner sc = new Scanner(fr);
    try {
      AGridCell[][] arr = null;
      while (sc.hasNext()) {
        int numRows = sc.nextInt();
        int numCols = sc.nextInt();
        Scanner getLine = new Scanner(sc.nextLine());
        arr = new AGridCell[numRows][numCols];
        for (int curRow = 0; curRow < numRows; curRow++) {
          String rowString = sc.next();
          for (int curCol = 0; curCol < numCols; curCol++) {
            arr[curRow][curCol] = stringToBoardCell(String.valueOf(rowString.charAt(curCol)));
          }
        }
      }
      return new Grid(arr);
    } catch (NoSuchElementException ex) {
      throw new IllegalArgumentException("scanner error");
    }
  }

  /**
   * to convert a string to a board cell.
   * @param s - the string
   * @return - a board cell
   */
  private static AGridCell stringToBoardCell(String s) {
    if (s.equals("C")) {
      return new CardCell();
    } else if (s.equals("X")) {
      return new HoleCell();
    } else {
      throw new IllegalArgumentException("must be C or X; given: " + s);
    }
  }
}
