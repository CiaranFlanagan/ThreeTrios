package cs3500.threetrios.test.model.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.GridCellCard;

/**
 * Tests for the GridCellCard class.
 */
public class TestGridCellCard {
  private GridCellAbstract cell1;

  @Before
  public void setUp() {
    cell1 = new GridCellCard();
  }

  @Test
  public void testCanHaveCard() {
    Assert.assertTrue(cell1.canHaveCard());
  }

  @Test
  public void testHasCard() {
    Assert.assertFalse(cell1.hasCard());
  }

  @Test
  public void testGetCard() {
    Assert.assertNull(cell1.getCard());
  }


}
