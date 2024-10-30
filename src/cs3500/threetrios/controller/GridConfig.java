package cs3500.threetrios.controller;

import cs3500.threetrios.model.AGridCell;
import cs3500.threetrios.model.CardCell;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.HoleCell;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * To convert a file to a GridBoard.
 */
public class GridConfig {
  /**
   * take a scanner and make a grid.
   * @param sc - the scanner
   * @return - a grid
   */
  public static Grid scannerToGrid(Scanner sc) {
    if (sc == null) {
      throw new IllegalArgumentException("null scanner");
    }
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
