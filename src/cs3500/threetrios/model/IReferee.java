package cs3500.threetrios.model;

/**
 * To represent a referee enforcing the rules of battle phases in a game of three trios.
 */
public interface IReferee {
  /**
   * To referee a's battle phase.
   * @param a - a board cell
   */
  void refereeBattlePhase(GridCellAbstract a);

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

  /**
   * To set the attack value of [card] in direction [cd] to [av].
   * @param card - the card to mutate
   * @param av - the attack value to set card's to
   * @param cd - the direction in which to set it
   */
  default void setCardAttackValueInDirection(Card card, AttackValue av, CardinalDirection cd) {
    card.setAttackValueInDirection(av, cd);
  }

  /**
   * To set the coach of the card to [newCoach].
   * @param card - the card to mutate
   * @param newCoach - the value to pass as the new coach
   */
  default void setCardCoach(Card card, Coach newCoach) {
    card.setCoach(newCoach);
  }
}
