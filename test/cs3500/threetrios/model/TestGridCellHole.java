package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TestGridCellHole {
  private GridCellAbstract cell1;
  private GridCellAbstract cell2;
  private GridCellAbstract cell3;
  private GridCellAbstract cell4;
  @Before
  public void setUp() {
    cell1 = new GridCellHole();
    cell2 = new GridCellHole();
    cell3 = new GridCellHole();
    cell4 = new GridCellHole();
    cell1.link(cell2, CardinalDirection.EAST);
    cell3.link(cell4, CardinalDirection.NORTH);
  }

  //need linked cells so even through is public, it in this class
  @Test
  public void testHasNeighborToThe() {
    Assert.assertTrue(cell1.hasNeighborToThe(CardinalDirection.EAST));
  }

  //need linked cells so even through is public, it in this class
  @Test
  public void testGetNeighborToThe() {
    cell1.getNeighborToThe(CardinalDirection.EAST);
    Assert.assertEquals(cell2, cell1.getNeighborToThe(CardinalDirection.EAST));
  }

  @Test
  public void testLink() {
    cell1.link(cell2, CardinalDirection.WEST);
    Assert.assertEquals(cell2, cell1.getNeighborToThe(CardinalDirection.WEST));
    Assert.assertEquals(cell1, cell2.getNeighborToThe(CardinalDirection.EAST));
    Assert.assertEquals(cell3, cell4.getNeighborToThe(CardinalDirection.SOUTH));
    Assert.assertEquals(cell4, cell3.getNeighborToThe(CardinalDirection.NORTH));
  }

  @Test(expected = IllegalStateException.class)
  public void testPlaceCard() {
    cell1.placeCard(new Card("Red", new HashMap<>()));
  }

  @Test
  public void testRenderTextConstructor() {
    Assert.assertEquals("X", cell1.renderTextConstructor());
  }

  @Test(expected = IllegalStateException.class)
  public void testAcceptBattlePhase() {
    Referee ref = new RefereeDefault();
    cell1.acceptBattlePhase(ref);
  }

}
