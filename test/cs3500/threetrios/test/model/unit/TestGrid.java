package cs3500.threetrios.test.model.unit;

import cs3500.threetrios.model.CellType;
import org.junit.Before;
import org.junit.Test;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellReadOnly;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Grid class.
 */
public class TestGrid {

  private Grid grid;
  CellType[][] constructorArg;

  @Before
  public void setUp() {
    constructorArg = new CellType[2][2];
    constructorArg[0][0] = CellType.CARD;
    constructorArg[0][1] = CellType.CARD;
    constructorArg[1][0] = CellType.CARD;
    constructorArg[1][1] = CellType.CARD;
    grid = new Grid(constructorArg);
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
  public void testReadOnlyGridArr() {
    GridCellReadOnly[][] readOnlyGrid = grid.readOnlyArray2D();
    assertEquals(constructorArg.length, readOnlyGrid.length);
    assertEquals(constructorArg[0].length, readOnlyGrid[0].length);
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
    CellType[][] invalidGrid = new CellType[0][0];
    Grid grid = new Grid(invalidGrid);
  }



}