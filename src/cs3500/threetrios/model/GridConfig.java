package cs3500.threetrios.model;

import cs3500.threetrios.model.done.ABoardCell;
import cs3500.threetrios.model.done.AttackValue;
import cs3500.threetrios.model.done.CardCell;
import cs3500.threetrios.model.done.CardinalDirection;
import cs3500.threetrios.model.done.HoleCell;
import cs3500.threetrios.model.done.TTCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GridConfig {
  public static GridBoard fileToGridBoard(File f) {
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
      ABoardCell[][] arr = null;
      while (sc.hasNext()) {
        int numRows = sc.nextInt();
        int numCols = sc.nextInt();
        Scanner getLine = new Scanner(sc.nextLine());
        arr = new ABoardCell[numRows][numCols];
        for (int curRow = 0; curRow < numRows; curRow++) {
          String rowString = sc.next();
          for (int curCol = 0; curCol < numCols; curCol++) {
            arr[curRow][curCol] = stringToBoardCell(String.valueOf(rowString.charAt(curCol)));
          }
        }
      }
      return new GridBoard(arr);
    } catch (NoSuchElementException ex) {
      throw new IllegalArgumentException("scanner error");
    }
  }

  private static ABoardCell stringToBoardCell(String s) {
    if (s.equals("C")) {
      return new CardCell();
    } else if (s.equals("X")) {
      return new HoleCell();
    } else {
      throw new IllegalArgumentException("must be C or X; given: " + s);
    }
  }
}
