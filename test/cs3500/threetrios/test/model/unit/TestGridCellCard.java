package cs3500.threetrios.test.model.unit;

import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.GridCellAbstract;

public class TestGridCellCard {
  private GridCellAbstract cell1;
  private GridCellAbstract cell2;
  @Before
  public void setUp() {
    GridCellAbstract[][] grid = new GridCellAbstract[2][2];
    GridCellAbstract cell1 = grid[0][0];
    GridCellAbstract cell2 = grid[0][1];
  }

  @Test
  public void testHasNeighborToThe() {
    cell1.hasNeighborToThe(CardinalDirection.EAST);
  }

  @Test
  public void testGetNeighborToThe() {

  }

  @Test
  public void testCanHaveCard() {

  }

  @Test
  public void testHasCard() {

  }

  @Test
  public void testGetHand() {

  }

}
