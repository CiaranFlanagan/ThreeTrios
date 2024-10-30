package cs3500.threetrios.model;

/**
 * to represent a referee enforcing the rules of battle phases in a game of three trios.
 */
public interface BattlePhaseReferee {
  /**
   * to referee a's battle phase
   * @param a - a board cell
   */
  void refereeBattlePhase(AGridCell a);

  /**
   * to referee a's battle phase
   * @param c - a card cell
   */
  void refereeBattlePhase(CardCell c);

  /**
   * to referee h's battle phase
   * @param h - a hole cell
   */
  void refereeBattlePhase(HoleCell h);

  /**
   * to return the [ab]'s neighbor to the [direction]
   * @param direction - the direction to consider
   * @param ab - the board cell to consider
   * @return - the neighbor
   */
  default AGridCell getCellNeighborToThe(CardinalDirection direction, AGridCell ab) {
    return ab.getNeighborToThe(direction);
  }

  /**
   * to evaluate whether [ab] has a card
   * @param ab - the board cell to consider
   * @return - if ab has a card
   */
  default boolean doesCellHaveCard(AGridCell ab) {
    return ab.hasCard();
  }

  /**
   * to return the card in the [ab]
   * @param ab - the board cell
   * @return - the card in [ab]
   */
  default Card getCellCard(AGridCell ab) {
    return ab.getCard();
  }

  /**
   * to set the attack value of [card] in direction [cd] to [av]
   * @param card - the card to mutate
   * @param av - the attack value to set card's to
   * @param cd - the direction in which to set it
   */
  default void setCardAttackValueInDirection(Card card, AttackValue av, CardinalDirection cd) {
    card.setAttackValueInDirection(av, cd);
  }

  /**
   * to return the coach of the given card
   * @param card - the card to read
   * @return - the coach of card
   */
  default Coach getCardCoach(Card card) {
    return card.getCoach();
  }

  /**
   * to set the coach of the card to [newCoach]
   * @param card - the card to mutate
   * @param newCoach - the value to pass as the new coach
   */
  default void setCardCoach(Card card, Coach newCoach) {
    card.setCoach(newCoach);
  }
}
