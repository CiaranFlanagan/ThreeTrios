package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

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

  @Test
  public void canHaveCard() {
    Assert.assertTrue(cell1.canHaveCard());
  }

  //public method but uses place card, so must be in here
  @Test
  public void testGetCard() {
    Card c = new Card("Red", new HashMap<>());
    cell1.placeCard(c);
    Assert.assertEquals(c ,cell1.getCard());
  }
}
