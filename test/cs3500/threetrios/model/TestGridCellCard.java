package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;

public class TestGridCellCard {
  private GridCellAbstract[][] grid;
  private GridCellAbstract cell1;
  private GridCellAbstract cell2;

  @Before
  public void setUp() {
    cell1 = grid[0][0];
    cell2 = grid[0][1];
    cell1.link(cell2, CardinalDirection.EAST);
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
  public void testGetHard() {

  }

}
