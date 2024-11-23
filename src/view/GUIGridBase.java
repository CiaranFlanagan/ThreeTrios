package view;

import model.Grid;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * To represent a base view only gui of a grid in Three Trios.
 */
public class GUIGridBase extends JPanel {

  protected final DrawGrid view;
  protected Grid curGrid;
  protected BufferedImage curImage;

  /**
   * Constructor.
   * @param gridView the artist of the grid
   */
  public GUIGridBase(DrawGrid gridView) {
    this.view = gridView;
    this.setVisible(true);
  }

  /**
   * To update the grid of this view only grid gui.
   * @param grid the grid to update this width
   */
  public void updateGrid(Grid grid) {
    this.curGrid = grid;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int numCols = curGrid.readOnlyArray2D()[0].length;
    int numRows = curGrid.readOnlyArray2D().length;
    int floorWidth = numCols * getWidth() / numCols;
    int floorHeight = numRows * getHeight() / numRows;
    curImage = new BufferedImage(floorWidth, floorHeight, 1);
    view.renderGrid(curGrid, curImage);
    g.drawImage(curImage, (getWidth() - floorWidth) / 2, 0, null);
  }

}
