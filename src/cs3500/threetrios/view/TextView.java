package cs3500.threetrios.view;

import java.util.Map;

import cs3500.threetrios.model.AGridCell;
import cs3500.threetrios.model.AttackValue;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.HoleCell;
import cs3500.threetrios.model.IModel;

/**
 * Represents a textual view of the game.
 */
public class TextView implements ThreeTriosView {
  private final IModel model;

  /**
   * Constructs a textual view of the game.
   * @param model the model
   */
  public TextView(IModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
  }

  /**
   * Renders the game as a string.
   * @return the game as a string
   */
  @Override
  public String renderGame() {
    StringBuilder sb = new StringBuilder();
    Coach curCoach = model.getCurrentCoach();
    sb.append("Player: ").append(curCoach.toString().toUpperCase()).append("\n");
    Grid grid = model.getGrid();
    for (AGridCell[] row : grid.getGrid()) {
      for (AGridCell cell : row) {
        if (cell instanceof HoleCell) {
          sb.append(" ");
          //changed has card from protected to public
        } else if (!cell.hasCard()) {
          sb.append("_");
        } else {
          //changed get card from protected to public
          Card card = cell.getCard();
          String coachInitial = card.getCoach().toString().substring(0, 1).toUpperCase();
          sb.append(coachInitial).append(" ");
        }
      }
      sb.append("\n");
    }
    sb.append("Hand:\n");
    for (Card card : curCoach.getHand()) {
      sb.append(card.getName()).append(" ");
      Map<CardinalDirection, AttackValue> attackValues = card.getAttackValues();
      for (CardinalDirection dir : CardinalDirection.values()) {
        sb.append(attackValues.get(dir)).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
