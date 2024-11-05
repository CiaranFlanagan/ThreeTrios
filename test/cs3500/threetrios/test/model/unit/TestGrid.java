package cs3500.threetrios.test.model.unit;

import org.junit.Before;
import org.junit.Test;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellAbstract;
import cs3500.threetrios.model.GridCellCard;
import cs3500.threetrios.model.GridCellReadOnly;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Grid class.
 */
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

  @Test(expected = IllegalArgumentException.class)
  public void testNullGrid() {
    grid = new Grid(null);
  }

  @Test
  public void testGetNumHoles() {
    assertEquals(0, grid.getNumHoles());
  }

  @Test
  public void testReadOnly2dCellArray() {
    GridCellReadOnly[][] readOnlyGrid = grid.readOnly2dCellArray();
    assertEquals(gridCells.length, readOnlyGrid.length);
    assertEquals(gridCells[0].length, readOnlyGrid[0].length);
  }

  @Test
  public void testToString() {
    String expected = "2 2\nCC\nCC";
    assertEquals(expected, grid.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullGrid() {
    Grid grid = new Grid(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidGrid() {
    GridCellAbstract[][] invalidGrid = new GridCellAbstract[0][0];
    Grid grid = new Grid(invalidGrid);
  }



}