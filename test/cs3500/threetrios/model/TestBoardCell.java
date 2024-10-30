package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

public class TestBoardCell {
  @Test
  public void testLink1() {
    BoardCellMockTestLinks s = new BoardCellMockTestLinks();
    BoardCellMockTestLinks n = new BoardCellMockTestLinks();
    s.link(n, CardinalDirection.NORTH);
    Assert.assertTrue(s.hasNeighborIn(CardinalDirection.NORTH));
    Assert.assertTrue(n.hasNeighborIn(CardinalDirection.SOUTH));

    BoardCellMockTestLinks e = new BoardCellMockTestLinks();
    BoardCellMockTestLinks w = new BoardCellMockTestLinks();
    e.link(w, CardinalDirection.WEST);
    Assert.assertTrue(e.hasNeighborIn(CardinalDirection.WEST));
    Assert.assertTrue(w.hasNeighborIn(CardinalDirection.EAST));
  }
}

