package cs3500.threetrios.test.model.unit;

import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.GridCellCard;
import cs3500.threetrios.model.GridCellHole;
import cs3500.threetrios.utils.LineWriter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Grid class.
 */
public class TestGridLinks {
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

}
