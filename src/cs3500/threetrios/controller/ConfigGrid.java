package cs3500.threetrios.controller;

import cs3500.threetrios.model.CellType;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellVisitable;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * To convert a file to a GridBoard.
 */
public class ConfigGrid {
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
      CellType[][] arr = null;
      while (sc.hasNext()) {
        int numRows = sc.nextInt();
        int numCols = sc.nextInt();
        Scanner getLine = new Scanner(sc.nextLine());
        arr = new CellType[numRows][numCols];
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
  private static CellType stringToBoardCell(String s) {
    if (s.equals("C")) {
      return CellType.CARD;
    } else if (s.equals("X")) {
      return CellType.HOLE;
    } else {
      throw new IllegalArgumentException("must be C or X; given: " + s);
    }
  }
}
