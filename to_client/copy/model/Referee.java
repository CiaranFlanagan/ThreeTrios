package copy.model;

/**
 * To represent a referee enforcing the rules of battle phases in a game of three trios.
 */
public interface Referee {

  /**
   * To referee a's battle phase.
   * @param a - a board cell
   */
  void refereeBattlePhase(GridCellVisitable a);

  /**
   * To referee a's battle phase.
   * @param c - a card cell
   */
  void refereeBattlePhase(GridCellCard c);

  /**
   * To referee h's battle phase.
   * @param h - a hole cell
   */
  void refereeBattlePhase(GridCellHole h);


}
