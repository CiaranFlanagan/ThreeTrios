package model;

/**
 * Referee where lesser value beats greater value.
 */
public class RefereeNegate extends RefereeDefault {

  @Override
  public boolean fightCardsAcc(Card us, Card them, CardinalDirection dir, boolean prevBeats) {
    return !prevBeats;
  }
}
