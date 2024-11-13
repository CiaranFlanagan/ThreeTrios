package cs3500.threetrios.model;

import cs3500.threetrios.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Grid class.
 */
public class TestGrid {
  private Grid grid;

  @Before
  public void setUp() {
    CellType[][] twobytwoallcards = new CellType[2][2];
    twobytwoallcards[0][0] = CellType.CARD;
    twobytwoallcards[0][1] = CellType.CARD;
    twobytwoallcards[1][0] = CellType.CARD;
    twobytwoallcards[1][1] = CellType.CARD;
    grid = new Grid(twobytwoallcards);
  }

  @Test
  public void testPlaceCardOn() {
    HashMap<Object, Object> hm = new HashMap<>();
    Card card = Utils.makeCard("a 1 1 1 1");
    GridCellAbstract cell = grid.placeCardOn(0, 0, card);
    assertTrue(cell.hasCard());
    System.out.println(cell);
    assertEquals(card.toString(), cell.getCard().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardOnInvalidPositionSmallRow() {
    Card card = new Card("TestCard", new HashMap<>());
    grid.placeCardOn(-1, 0, card);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardOnInvalidPositionLargeRow() {
    Card card = new Card("TestCard", new HashMap<>());
    grid.placeCardOn(2, 0, card);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardOnInvalidPositionSmallCol() {
    Card card = new Card("TestCard", new HashMap<>());
    grid.placeCardOn(0, -1, card);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardOnInvalidPositionLargeCol() {
    Card card = new Card("TestCard", new HashMap<>());
    grid.placeCardOn(0, 2, card);
  }

  @Test
  public void testIsFull() {
    assertFalse(grid.isFull());
    Card card = new Card("TestCard", new HashMap<>());
    grid.placeCardOn(0, 0, card);
    grid.placeCardOn(0, 1, card);
    grid.placeCardOn(1, 0, card);
    grid.placeCardOn(1, 1, card);
    assertTrue(grid.isFull());
  }
}
