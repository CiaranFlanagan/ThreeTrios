package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the BoardCell class.
 */
public class TestBoardCell {
  @Test
  public void testLink1() {
    GridCellMock s = new GridCellMock();
    GridCellMock n = new GridCellMock();
    s.link(n, CardinalDirection.NORTH);
    Assert.assertTrue(s.hasNeighborIn(CardinalDirection.NORTH));
    Assert.assertTrue(n.hasNeighborIn(CardinalDirection.SOUTH));

    GridCellMock e = new GridCellMock();
    GridCellMock w = new GridCellMock();
    e.link(w, CardinalDirection.WEST);
    Assert.assertTrue(e.hasNeighborIn(CardinalDirection.WEST));
    Assert.assertTrue(w.hasNeighborIn(CardinalDirection.EAST));
  }
}

