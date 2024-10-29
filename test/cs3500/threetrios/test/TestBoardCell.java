package cs3500.threetrios.test;

import cs3500.threetrios.model.IPlayer;
import cs3500.threetrios.model.done.ABoardCell;
import cs3500.threetrios.model.done.CardCell;
import cs3500.threetrios.model.done.CardinalDirection;
import cs3500.threetrios.model.done.HoleCell;
import cs3500.threetrios.model.done.TTCard;
import org.junit.Assert;
import org.junit.Test;

public class TestBoardCell {
  @Test
  public void testLink1() {
    LinkMock s = new LinkMock();
    LinkMock n = new LinkMock();
    s.link(n, CardinalDirection.NORTH);
    Assert.assertTrue(s.hasNeighborIn(CardinalDirection.NORTH));
    Assert.assertTrue(n.hasNeighborIn(CardinalDirection.SOUTH));

    LinkMock e = new LinkMock();
    LinkMock w = new LinkMock();
    e.link(w, CardinalDirection.WEST);
    Assert.assertTrue(e.hasNeighborIn(CardinalDirection.WEST));
    Assert.assertTrue(w.hasNeighborIn(CardinalDirection.EAST));
  }
}

class LinkMock extends ABoardCell {

  LinkMock() {

  }
  /**
   *
   */
  @Override
  public void battleAllPossible() {
    // do nothing for now
  }

  /**
   * @param card
   * @param dir
   * @param maybeNewOwner
   */
  @Override
  public void battleAndCascadeOnLose(TTCard card, CardinalDirection dir, IPlayer maybeNewOwner) {
    // do nothing for now
  }

  public boolean hasNeighborIn(CardinalDirection dir) {
    return neighbors.get(dir) != null;
  }


}
