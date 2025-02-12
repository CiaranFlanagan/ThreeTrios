package model;

/**
 * To abstract and bundle side effect of cards for Referee's. Necessary for certain
 * implementations of Three trios.
 */
public abstract class RefereeAbstract implements Referee {

  /**
   * To set the attack value of [card] in direction [cd] to [av].
   * @param card - the card to mutate
   * @param av   - the attack value to set card's to
   * @param cd   - the direction in which to set it
   */
  protected void setCardAttackValueInDirection(Card card,
                                               AttackValue av,
                                               CardinalDirection cd) {
    card.setAttackValueInDirection(av, cd);
  }

  /**
   * To set the coach of the card to [newCoach].
   * @param card     - the card to mutate
   * @param newCoach - the value to pass as the new coach
   */
  protected void setCardCoach(Card card, CoachColor newCoach) {
    card.setCoach(newCoach);
  }


}
