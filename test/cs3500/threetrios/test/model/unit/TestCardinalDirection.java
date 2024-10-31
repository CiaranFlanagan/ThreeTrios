package cs3500.threetrios.test.model.unit;

import org.junit.Assert;
import org.junit.Test;

import cs3500.threetrios.model.CardinalDirection;

public class TestCardinalDirection {
  public void setUp() {
    CardinalDirection dirNorth = CardinalDirection.NORTH;
    CardinalDirection dirSouth = CardinalDirection.SOUTH;
    CardinalDirection dirEast = CardinalDirection.EAST;
    CardinalDirection dirWest  = CardinalDirection.WEST;
  }

  @Test
  public void TestOppositeValidDirections() {
    Assert.assertEquals(CardinalDirection.NORTH.opposite(), CardinalDirection.SOUTH);
    Assert.assertEquals(CardinalDirection.SOUTH.opposite(), CardinalDirection.NORTH);
    Assert.assertEquals(CardinalDirection.EAST.opposite(), CardinalDirection.WEST);
    Assert.assertEquals(CardinalDirection.WEST.opposite(), CardinalDirection.EAST);
  }
}
