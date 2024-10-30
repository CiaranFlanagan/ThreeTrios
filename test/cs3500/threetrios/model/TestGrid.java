package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

public class TestGrid {
  @Test
  public void test1() {
    AGridCell[][] grid = new AGridCell[2][2];
    grid[0][0] = new HoleCell();
    grid[0][1] = new CardCell();
    grid[1][0] = new HoleCell();
    grid[1][1] = new HoleCell();
    Grid gb = new Grid(grid);
    System.out.println(gb);
  }

  @Test
  public void test2() {
    AGridCell[][] bcs = new AGridCell[1][1];
    bcs[0][0] = new HoleCell();
    Grid gb = new Grid(bcs);
    System.out.println(gb);
  }

  @Test
  public void test3() {
    GridCellMockTestLinks[][] grid = new GridCellMockTestLinks[2][2];
    grid[0][0] = new GridCellMockTestLinks();
    grid[0][1] = new GridCellMockTestLinks();
    grid[1][0] = new GridCellMockTestLinks();
    grid[1][1] = new GridCellMockTestLinks();
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
