package cs3500.threetrios.model;

import cs3500.threetrios.utils.LineWriter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Grid class.
 */
public class TestGrid {
  @Test
  public void test1() {
    GridCellAbstract[][] grid = new GridCellAbstract[2][2];
    grid[0][0] = new GridCellHole();
    grid[0][1] = new GridCellCard();
    grid[1][0] = new GridCellHole();
    grid[1][1] = new GridCellHole();
    Grid gb = new Grid(grid);
    String expected = LineWriter.create().line("2 2").line("XC").endWith("XX").toString();
    Assert.assertEquals(expected, gb.toString());
  }

  @Test
  public void test2() {
    GridCellAbstract[][] bcs = new GridCellAbstract[1][1];
    bcs[0][0] = new GridCellHole();
    Grid gb = new Grid(bcs);
    String expected = LineWriter.create().line("1 1").endWith("X").toString();
    Assert.assertEquals(expected, gb.toString());
  }

  @Test
  public void test3() {
    GridCellMock[][] grid = new GridCellMock[2][2];
    grid[0][0] = new GridCellMock();
    grid[0][1] = new GridCellMock();
    grid[1][0] = new GridCellMock();
    grid[1][1] = new GridCellMock();
    Grid gb = new Grid(grid); // after construction, they should be linked
    Assert.assertTrue(grid[0][0].hasNeighborIn(CardinalDirection.EAST));
    Assert.assertTrue(grid[0][0].hasNeighborIn(CardinalDirection.SOUTH));
    Assert.assertTrue(grid[0][1].hasNeighborIn(CardinalDirection.WEST));
    Assert.assertTrue(grid[0][1].hasNeighborIn(CardinalDirection.SOUTH));
    Assert.assertTrue(grid[1][0].hasNeighborIn(CardinalDirection.EAST));
    Assert.assertTrue(grid[1][0].hasNeighborIn(CardinalDirection.NORTH));
    Assert.assertTrue(grid[1][1].hasNeighborIn(CardinalDirection.WEST));
    Assert.assertTrue(grid[1][1].hasNeighborIn(CardinalDirection.NORTH));
  }
}
