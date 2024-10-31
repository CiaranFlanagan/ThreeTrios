package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestGrid {
  private Grid grid;
  private GridCellAbstract[][] gridCells;

  @Before
  public void setUp() {
    gridCells = new GridCellAbstract[2][2];
    gridCells[0][0] = new GridCellCard();
    gridCells[0][1] = new GridCellCard();
    gridCells[1][0] = new GridCellCard();
    gridCells[1][1] = new GridCellCard();
    grid = new Grid(gridCells);
  }
  @Test
  public void testPlaceCardOn() {
    HashMap<Object, Object> hm = new HashMap<>();
    Card card = new Card("TestCard", new HashMap<>());
    GridCellAbstract cell = grid.placeCardOn(0, 0, card);
    assertTrue(cell.hasCard());
    assertEquals(card, cell.getCard());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardOnInvalidPosition() {
    Card card = new Card("TestCard", new HashMap<>());
    grid.placeCardOn(-1, 0, card);
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
