package view;

import model.Grid;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GUIGridBase extends JPanel {

  protected Grid curGrid;
  protected final DrawGrid view;
  protected BufferedImage curImage;

  public GUIGridBase(DrawGrid view) {
    this.view = view;
    this.setVisible(true);
  }

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
