package view;

import model.Grid;
import model.GridCellReadOnly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * To render a grid onto a buffered image. This is used so that a grid can directly be
 * rendered to a png to test.
 */
public class DrawGrid {

  protected static final Color VISIBLE_HOLE = Color.GRAY;
  protected static final Color VISIBLE_EMPTY_CARD = new Color(144, 238, 144);
  protected static final Color VISIBLE_BORDER = Color.BLACK;

  /**
   * To render a grid onto a buffered image.
   * @param grid the grid to render
   * @param image the image to render onto
   */
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
          drawCell(artist, cell, row, col, cellWidth, cellHeight);
        }
        artist.translate(cellWidth, 0);
      }
      artist = copy;
      artist.translate(0, cellHeight);
    }
  }

  /**
   * To produce the cell width each cell should be given the image that the cells need
   * to be rendered onto. The formula for this is to get the width of the image and
   * divide by the number of columns in the grid.
   * @param grid the grid to get the number of rows from
   * @param image the image to judge width based on
   * @return the width each cell should be
   */
  private int cellWidth(Grid grid, BufferedImage image) {
    return image.getWidth() / grid.numCols();
  }

  /**
   * To produce the cell height each cell should be given the image that the cells need to
   * be rendered onto. The formula for this is to get the height of the image and
   * divide by
   * the number of rows in the grid.
   * @param grid  the grid to get the number of columns from
   * @param image the image to judge height based on
   * @return the height each cell should be
   */
  private int cellHeight(Grid grid, BufferedImage image) {
    return image.getHeight() / grid.numRows();
  }

  /**
   * To draw a cell.
   * @param artist the graphics object with context
   * @param cell the cell to draw
   * @param cellWidth the width of the cell in pixels
   * @param cellHeight the height of the cell in pixels
   */
  protected void drawCell(Graphics artist,
                        GridCellReadOnly cell,
                        int row,
                        int col,
                        int cellWidth,
                        int cellHeight) {
    artist.setColor(cell.canHaveCard() ? VISIBLE_EMPTY_CARD : VISIBLE_HOLE);
    artist.fillRect(0, 0, cellWidth, cellHeight);
    artist.setColor(VISIBLE_BORDER);
    artist.drawRect(0, 0, cellWidth, cellHeight);
  }

  /**
   * To take a grid and an image and analyze what cell the given point would correspond
   * to.
   * @param pixel the pixel to analyze
   * @param grid the grid that was drawn onto image
   * @param image the image of the grid
   * @return what cell, a point in (rows, cols), the pixel would be at
   */
  public Point cellAt(Point pixel, Grid grid, BufferedImage image) {
    return new Point((int) pixel.getX() * grid.numCols() / image.getWidth(),
                     (int) pixel.getY() * grid.numRows() / image.getHeight());
  }

}
