package model;

import java.util.List;
import java.util.ArrayList;

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
