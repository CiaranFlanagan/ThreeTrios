package cs3500.threetrios.controller;

import cs3500.threetrios.utils.extensions.MouseHandler;
import cs3500.threetrios.utils.extensions.WasMouse;
import cs3500.threetrios.view.ViewGUI;

import java.awt.event.MouseEvent;

public class HandleClickForGrid extends MouseHandler {
  ViewGUI.GridGUI gridGUI;

  /**
   * constructor.
   * @param gridGUI - a grid gui
   */
  public HandleClickForGrid(ViewGUI.GridGUI gridGUI) {
    this.gridGUI = gridGUI;
    map.put(WasMouse.CLICKED, this::handleClick);

  }

  /**
   * To add this as a listener to its grid.
   */
  public void init() {
    gridGUI.addMouseListener(this);
  }

  private void handleClick(MouseEvent me) {
    System.out.print("x: " + gridGUI.modelx(me.getX()));
    System.out.println(" y: " + gridGUI.modely(me.getY()));
  }

}
