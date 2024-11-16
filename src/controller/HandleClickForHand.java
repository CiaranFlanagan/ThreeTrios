package controller;

import model.ModelReadOnly;
import utils.IntPoint2D;
import utils.MouseHandler;
import utils.WasMouse;
import view.ViewGUI;

import java.awt.event.MouseEvent;

/**
 * To handle a click for a hand component. right now, it just selects/deselects a card from hand
 * and tells the view to render.
 */
public class HandleClickForHand extends MouseHandler {

  ViewGUI.HandGUI hand;
  ModelReadOnly modelReadOnly;

  /**
   * Constructs a HandleClickForHand instance.
   *
   * @param hand the HandGUI instance representing the player's hand
   * @param modelReadOnly a read-only model instance for accessing game state
   */
  public HandleClickForHand(ViewGUI.HandGUI hand, ModelReadOnly modelReadOnly) {
    this.hand = hand;
    this.modelReadOnly = modelReadOnly;
    map.put(WasMouse.CLICKED, this :: toDoToHand);
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
      System.err.println("Player  " + modelReadOnly.curCoach().opponent()
                             + " it is not your turn");
      return;
    }

    IntPoint2D modelCoordinates = new IntPoint2D(hand.modelx(me.getX()), hand.modely(me.getY()));
    // if they click when nothing is selected
    if (hand.getSelected().isEmpty()) {
      handleSelect(modelCoordinates);
    } else if (hand.getSelected().isPresent()) {
      // if there is a selected card
      if (hand.getSelected().get().equals(modelCoordinates)) {
        // if they click on the same card
        hand.deselect();
      } else {
        // if they click on a different card
        handleSelect(modelCoordinates);
      }
    }

    hand.repaint();
  }

  private void handleSelect(IntPoint2D modelCoordinates) {
    int idx = modelCoordinates.y;
    if (idx >= modelReadOnly.curCoachesHands().get(modelReadOnly.curCoach()).size()) {
      return;
    }
    hand.select(modelCoordinates);
    System.out.println("Player: " + hand.getCoach() + ", Card: " + modelCoordinates.y);
  }

  /**
   * Initializes the mouse handler by adding it as a listener to the hand GUI.
   */
  public void init() {
    hand.addMouseListener(this);
  }

}
