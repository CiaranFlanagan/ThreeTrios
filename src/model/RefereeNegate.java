package model;

public class RefereeNegate extends RefereeDefault {

  @Override
  public boolean fightCardsAcc(Card us, Card them, CardinalDirection dir, boolean prevBeats) {
    return !us.beats(them, dir);
  }


}
