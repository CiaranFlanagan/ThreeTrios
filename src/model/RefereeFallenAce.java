package model;


/**
 * Referee where 1 beats A.
 */
public class RefereeFallenAce extends RefereeDefault {
  @Override
  public boolean fightCardsAcc(Card us, Card them, CardinalDirection dir, boolean prevBeats) {
    AttackValue ourVal = us.getAttackValue(dir);
    AttackValue theirVal = them.getAttackValue(dir.opposite());

    if (ourVal == AttackValue.ONE && theirVal == AttackValue.A) {
      return true;
    }

    return prevBeats;
  }
}
