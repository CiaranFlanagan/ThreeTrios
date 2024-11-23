package utils;

import model.CellType;
import model.Grid;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * To convert a txt file to a GridBoard, representing the board of a Three Trios Game
 */
public class ConfigGrid {

  /**
   * Take a scanner and make a grid.
   *
   * @param sc - the scanner
   * @return - a grid
   */
  public static Grid scannerToGrid(Supplier<Scanner> scannerSupplier) {
    if (scannerSupplier == null) {
      throw new IllegalArgumentException("null supplier");
    }
    Scanner sc = scannerSupplier.get();
    return scannerToGrid(sc);
  }

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
            arr[curRow][curCol] =
                stringToBoardCell(String.valueOf(rowString.charAt(curCol)));
          }
        }
      }
      sc.close();
      return new Grid(arr);
    } catch (NoSuchElementException ex) {
      throw new IllegalArgumentException("scanner error");
    }
  }

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
