package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

public class TestBoardCell {
  @Test
  public void testLink1() {
    GridCellMockTestLinks s = new GridCellMockTestLinks();
    GridCellMockTestLinks n = new GridCellMockTestLinks();
    s.link(n, CardinalDirection.NORTH);
    Assert.assertTrue(s.hasNeighborIn(CardinalDirection.NORTH));
    Assert.assertTrue(n.hasNeighborIn(CardinalDirection.SOUTH));

    GridCellMockTestLinks e = new GridCellMockTestLinks();
    GridCellMockTestLinks w = new GridCellMockTestLinks();
    e.link(w, CardinalDirection.WEST);
    Assert.assertTrue(e.hasNeighborIn(CardinalDirection.WEST));
    Assert.assertTrue(w.hasNeighborIn(CardinalDirection.EAST));
  }
}

