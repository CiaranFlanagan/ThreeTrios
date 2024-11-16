package view;

import model.Card;
import model.CardinalDirection;
import model.Coach;
import model.GridCellReadOnly;
import model.Model;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * Represents a textual view of the game.
 */
public class ViewTextBase implements View<PrintStream> {

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
  public void renderTo(PrintStream out) {
    String renderedString = renderString();
    try {
      if (log != null) {
        log.append(renderedString);
      }
    } catch (IOException ex) {
      throw new IllegalStateException("bad appendable");
    }
    out.println(renderedString);
  }

  /**
   * Renders the game as a string.
   *
   * @return the game as a string
   */
  private String renderString() {
    StringBuilder sb = new StringBuilder();
    Coach curCoach = model.curCoach();
    List<Card> curCoachHand = model.curCoachesHands().get(curCoach);
    sb.append("Player: ").append(curCoach.toString().toUpperCase()).append("\n");
    GridCellReadOnly[][] grid = model.curGrid().readOnlyArray2D();
    for (GridCellReadOnly[] row : grid) {
      for (GridCellReadOnly cell : row) {
        if (!cell.canHaveCard()) {
          sb.append(" ");
        } else if (!cell.hasCard()) {
          sb.append("_");
        } else {
          Card card = cell.getCard();
          String coachInitial = card.getCoach().toString().substring(0, 1).toUpperCase();
          sb.append(coachInitial);
        }
      }
      sb.append("\n");
    }
    sb.append("Hand:\n");
    for (Card card : model.curCoachesHands().get(curCoach)) {
      String cardString = card.getName();
      for (CardinalDirection direction : CardinalDirection.values()) {
        cardString += " " + card.getAttackValue(direction).toString();
      }
      sb.append(cardString + "\n");
    }
    return sb.toString().stripTrailing();
  }

}
