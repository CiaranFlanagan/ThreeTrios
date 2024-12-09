package view;

import java.awt.Graphics;
import java.util.function.IntBinaryOperator;

import model.GridCellReadOnly;

/**
 * Draws the grid with hints.
 */
public class DrawGridShowHints extends DrawGrid {
  protected IntBinaryOperator getScore;
  protected boolean toggleHints;

  public DrawGridShowHints() {
    getScore = (n1, n2) -> 0;
  }

  public void setNumFlippedIfPlaced(IntBinaryOperator getScore) {
    this.getScore = getScore;
  }

  public void toggleHints(boolean toggled) {
    toggleHints = toggled;
  }

  @Override
  protected void drawCell(Graphics artist, GridCellReadOnly cell, int row, int col, int
      cellWidth, int cellHeight) {
    super.drawCell(artist, cell, row, col, cellWidth, cellHeight);
    if (toggleHints) {
      System.out.println("toggled hints\nscore: " + getScore.applyAsInt(row, col) + "\n[r,c] = " +
          "[row, " + col + "]");
      artist.drawString(Integer.toString(getScore.applyAsInt(row, col)),
          cellWidth / 2,
          cellHeight / 2);
    }
  }
}
