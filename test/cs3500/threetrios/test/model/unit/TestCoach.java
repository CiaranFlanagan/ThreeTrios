package cs3500.threetrios.test.model.unit;

import cs3500.threetrios.model.Coach;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Coach class.
 */
public class TestCoach {

  @Test
  public void testOpp() {
    Assert.assertEquals(Coach.RED.opponent(), Coach.BLUE);
    Assert.assertEquals(Coach.BLUE.opponent(), Coach.RED);
  }

}
