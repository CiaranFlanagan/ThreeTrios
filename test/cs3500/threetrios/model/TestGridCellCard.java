package cs3500.threetrios.model;

import cs3500.threetrios.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Tests for the GridCellCard class.
 */
public class TestGridCellCard {
  private GridCellAbstract cell1;
  private GridCellAbstract cell2;
  private GridCellAbstract cell3;
  private GridCellAbstract cell4;

  @Before
  public void setUp() {
    cell1 = new GridCellCard();
    cell2 = new GridCellCard();
    cell3 = new GridCellCard();
    cell4 = new GridCellCard();
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

  //public but must be tested here because it used protected methods
  @Test
  public void testPlaceCard() {
    Card c = Utils.makeCard("b 1 2 3 4");
    cell1.placeCard(c);
    Assert.assertEquals(c, cell1.getCard());
  }

  @Test(expected = IllegalStateException.class)
  public void testPlaceCardTwice() {
    Card c = new Card("Red", new HashMap<>());
    cell1.placeCard(c);
    cell1.placeCard(c);
  }

  @Test
  public void testRenderTextConstructor() {
    Assert.assertEquals("C", cell1.renderTextConstructor());
  }

  @Test
  public void testAcceptBattlePhase() {
    Referee ref = new RefereeDefault();
    try {
      new GridCellCard().acceptBattlePhase(ref);
    } catch (Exception ignored) {
      Assert.fail("shouldn't throw exception");
    }

  }

}
