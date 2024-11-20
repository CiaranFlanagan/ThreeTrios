package view;

import model.Grid;
import model.GridCellReadOnly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DrawGrid {

  private static final Color VISIBLE_HOLE = Color.GRAY;
  private static final Color VISIBLE_EMPTY_CARD = new Color(144, 238, 144);
  private static final Color VISIBLE_BORDER = Color.BLACK;

  public void renderGrid(Grid grid, BufferedImage image) {
    Graphics artist = image.createGraphics();
    int cellWidth = cellWidth(grid, image);
    int cellHeight = cellHeight(grid, image);
    int numRows = grid.readOnlyArray2D().length;
    int numCols = grid.readOnlyArray2D()[0].length;
    artist.translate((image.getWidth() - cellWidth * numCols) / 2, 0);
    for (int row = 0; row < numRows; row += 1) {
      Graphics copy = artist.create();
      for (int col = 0; col < numCols; col += 1) {
        GridCellReadOnly cell = grid.readOnlyArray2D()[row][col];
        if (cell.hasCard()) {
          new DrawHand().renderCard(cell.getCard(), artist, cellWidth, cellHeight);
        } else {
          drawCell(artist, cell, cellWidth, cellHeight);
        }
        artist.translate(cellWidth, 0);
      }
      artist = copy;
      artist.translate(0, cellHeight);
    }
  }

  public Point cellAt(Point pixel, Grid grid, BufferedImage image) {
    return new Point((int) pixel.getX() * grid.numCols() / image.getWidth(),
                     (int) pixel.getY() * grid.numRows() / image.getHeight());
  }

  public Rectangle cellToBoundingBox(Point cell, Grid grid, BufferedImage image) {
    return null;
  }

  private void drawCell(Graphics artist,
                        GridCellReadOnly cell,
                        int cellWidth,
                        int cellHeight) {
    artist.setColor(cell.canHaveCard() ? VISIBLE_EMPTY_CARD : VISIBLE_HOLE);
    artist.fillRect(0, 0, cellWidth, cellHeight);
    artist.setColor(VISIBLE_BORDER);
    artist.drawRect(0, 0, cellWidth, cellHeight);
  }

  // TODO
  private int cellWidth(Grid grid, BufferedImage image) {
    return image.getWidth() / grid.readOnlyArray2D()[0].length;
  }

  // TODO
  private int cellHeight(Grid grid, BufferedImage image) {
    return image.getHeight() / grid.readOnlyArray2D().length;
  }


}
