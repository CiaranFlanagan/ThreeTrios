package cs3500.threetrios.controller;

import cs3500.threetrios.utils.extensions.MouseHandler;
import cs3500.threetrios.utils.extensions.WasMouse;
import cs3500.threetrios.view.ViewGUI;

import java.awt.event.MouseEvent;

/**
 * to handle a click for a hand component. right now, it just selects/deselects a card from hand
 * and tells the view to render.
 */
public class HandleClickForHand extends MouseHandler {
  ViewGUI.HandGUI hand;

  /**
   * constructor.
   * @param hand - a hand gui.
   */
  public HandleClickForHand(ViewGUI.HandGUI hand) {
    this.hand = hand;
    map.put(WasMouse.CLICKED, this::toDoToHand);
  }

  /**
   * To add this as a listener to its hand.
   */
  public void init() {
    hand.addMouseListener(this);
  }

  /**
   * Handles the click event on a hand component. If no card is currently selected, selects
   * the card at the clicked position and records the selection coordinates. If a card is
   * already selected, deselects it. The view is repainted to show the selection state.
   *
   * @param me the MouseEvent triggered by the click
   */
  public void toDoToHand(MouseEvent me) {
    if (!hand.selected()) {
      hand.select();
      hand.setXSelect(me.getX());
      hand.setYSelect(me.getY());
    } else {
      hand.deselect();
    }
    hand.repaint();
  }
}
