package cs3500.threetrios.model2;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.IReferee;
import cs3500.threetrios.model.GridCellCard;
import cs3500.threetrios.model.GridCellHole;

/**
 * Represents a possible new referee to make new rules.
 * The point of this class is to show that because of the default methods, this class
 * has the tools it needs to be extensible.
 */
public class PossibleNewRef implements IReferee {
  /**
   * To referee a's battle phase.
   *
   * @param a - a board cell
   */
  @Override
  public void refereeBattlePhase(GridCellAbstract a) {
    Card c = a.getCard();
    // c.setCoach(..) will error because of visibility
    this.setCardCoach(c, new Coach(Coach.Color.Red)); // works!
  }

  /**
   * To referee a's battle phase.
   *
   * @param c - a card cell
   */
  @Override
  public void refereeBattlePhase(GridCellCard c) {

  }

  /**
   * To referee h's battle phase.
   *
   * @param h - a hole cell
   */
  @Override
  public void refereeBattlePhase(GridCellHole h) {

  }
}
