package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

public class TestGridBoard {
  @Test
  public void test1() {
    ABoardCell[][] grid = new ABoardCell[2][2];
    grid[0][0] = new HoleCell();
    grid[0][1] = new CardCell();
    grid[1][0] = new HoleCell();
    grid[1][1] = new HoleCell();
    GridBoard gb = new GridBoard(grid);
    System.out.println(gb);
  }

  @Test
  public void test2() {
    ABoardCell[][] bcs = new ABoardCell[1][1];
    bcs[0][0] = new HoleCell();
    GridBoard gb = new GridBoard(bcs);
    System.out.println(gb);
  }

  @Test
  public void test3() {
    BoardCellMockTestLinks[][] grid = new BoardCellMockTestLinks[2][2];
    grid[0][0] = new BoardCellMockTestLinks();
    grid[0][1] = new BoardCellMockTestLinks();
    grid[1][0] = new BoardCellMockTestLinks();
    grid[1][1] = new BoardCellMockTestLinks();
    GridBoard gb = new GridBoard(grid); // after construction, they should be linked
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
