package cs3500.threetrios.view;

import java.io.IOException;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.GridCellHole;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.Model;

/**
 * Represents a textual view of the game.
 */
public class ViewTextBase implements View {
  private final Model model;
  private Appendable log;

  /**
   * Constructs a textual view of the game.
   *
   * @param model the model
   */
  public ViewTextBase(Model model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
  }

  /**
   * Constructs a textual view of the game.
   *
   * @param model the model
   */
  public ViewTextBase(Model model, Appendable appendable) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.log = appendable;
  }

  @Override
  public void render() {
    String renderedString = renderString();
    try {
      if (log != null) {
        log.append(renderedString);
      }
    } catch (IOException ex) {
      throw new IllegalStateException("bad appendable");
    }
    System.out.println(renderedString);
  }

  /**
   * Renders the game as a string.
   *
   * @return the game as a string
   */
  private String renderString() {
    StringBuilder sb = new StringBuilder();
    Coach curCoach = model.curCoach();
    sb.append("Player: ").append(curCoach.toString().toUpperCase()).append("\n");
    GridCellReadOnly[][] grid = model.curGrid().readOnlyArray2D();
    for (GridCellReadOnly[] row : grid) {
      for (GridCellReadOnly cell : row) {
        if (cell instanceof GridCellHole) {
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
      String cardString = card.getName();
      for (CardinalDirection direction : CardinalDirection.values()) {
        cardString += " " + card.getAttackValue(direction).toString();
      }
      sb.append(cardString + "\n");
    }
    return sb.toString().stripTrailing();
  }
}
