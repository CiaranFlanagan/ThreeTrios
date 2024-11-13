package cs3500.threetrios.test.model.unit;

import cs3500.threetrios.model.GridCellReadOnly;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.GridCellHole;

/**
 * Tests for the GridCellHole class.
 */
public class TestGridCellHole {
  private GridCellReadOnly cell1;

  @Before
  public void setUp() {
    cell1 = new GridCellHole();
  }

  @Test
  public void testCanHaveCard() {
    Assert.assertFalse(cell1.canHaveCard());
  }

  @Test
  public void testHasCard() {
    Assert.assertFalse(cell1.hasCard());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCard() {
    cell1.getCard();
  }
}