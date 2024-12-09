package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Refereree where Fallen Ace and Negate are applied at the same time.
 */
public class RefComposeBeats extends RefereeDefault {
  private List<Referee> refs;

  public RefComposeBeats(List<Referee> refs) {
    this.refs = new ArrayList(refs);
  }

  @Override
  public boolean fightCardsAcc(Card us, Card them, CardinalDirection dir, boolean prevBeats) {
    boolean acc = true;
    for (Referee ref : refs) {
      acc = ref.fightCardsAcc(us, them, dir, acc);
    }
    return acc;
  }
}
