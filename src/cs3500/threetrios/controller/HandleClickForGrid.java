package cs3500.threetrios.controller;

import cs3500.threetrios.utils.IntPoint2D;
import cs3500.threetrios.utils.extensions.MouseHandler;
import cs3500.threetrios.utils.extensions.WasMouse;
import cs3500.threetrios.view.ViewGUI;

import java.awt.event.MouseEvent;

/**
 * A handler for mouse clicks on a grid in the game's GUI.
 * Extends the MouseHandler class and adds functionality for
 * logging the position of mouse clicks relative to the game's
 * coordinate system.
 */
public class HandleClickForGrid extends MouseHandler {
  ViewGUI.GridGUI gridGUI;

  /**
   /**
   * Constructor for HandleClickForGrid.
   *
   * @param gridGUI the GridGUI instance this handler interacts with
   */
  public HandleClickForGrid(ViewGUI.GridGUI gridGUI) {
    this.gridGUI = gridGUI;
    map.put(WasMouse.CLICKED, this :: handleClick);

  }

  /**
   * Initializes the mouse handler by adding it as a listener to the grid GUI.
   */
  public void init() {
    gridGUI.addMouseListener(this);
  }

  private void handleClick(MouseEvent me) {
    System.out.println(
        new IntPoint2D(
            gridGUI.modelx(me.getX()),
            gridGUI.modely(me.getY())));
  }

}
