package cs3500.threetrios.controller;

import cs3500.threetrios.model.ModelReadOnly;
import cs3500.threetrios.utils.IntPoint2D;
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
  ModelReadOnly modelReadOnly;

  /**
   * constructor.
   *
   * @param hand - a hand gui.
   */
  public HandleClickForHand(ViewGUI.HandGUI hand, ModelReadOnly modelReadOnly) {
    this.hand = hand;
    this.modelReadOnly = modelReadOnly;
    map.put(WasMouse.CLICKED, this :: toDoToHand);
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
    if (modelReadOnly.curCoach() != hand.getCoach()) {
      System.err.println("not your turn");
      return;
    }

    IntPoint2D modelCoordinates = new IntPoint2D(hand.modelx(me.getX()), hand.modely(me.getY()));
    System.out.println(modelCoordinates);
    // if they click when nothing is selected
    if (hand.getSelected().isEmpty()) {
      hand.select(modelCoordinates);
    } else if (hand.getSelected().isPresent()) {
      // if there is a selected card
      if (hand.getSelected().get().equals(modelCoordinates)) {
        // if they click on the same card
        hand.deselect();
      } else {
        // if they click on a different card
        hand.select(modelCoordinates);
      }
    }

    hand.repaint();
  }
}
