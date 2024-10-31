package cs3500.threetrios.test.controller.unit;

import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellReadOnly;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Tests for the BoardConfig class.
 */
public class TestBoardConfig {
  @Test
  public void testFullVaried() {
    try {
      Grid grid = ConfigGrid.scannerToGrid(new Scanner(TestFiles.GRID_DISC_HOLES));
      GridCellReadOnly[][] cell2darr = grid.readOnly2dCellArray();
      Assert.assertEquals(cell2darr[0][0].canHaveCard(), true);
      Assert.assertEquals(cell2darr[0][1].canHaveCard(), true);
      Assert.assertEquals(cell2darr[0][2].canHaveCard(), false);
      Assert.assertEquals(cell2darr[1][0].canHaveCard(), false);
      Assert.assertEquals(cell2darr[1][1].canHaveCard(), true);
      Assert.assertEquals(cell2darr[1][2].canHaveCard(), true);
      Assert.assertEquals(cell2darr[2][0].canHaveCard(), true);
      Assert.assertEquals(cell2darr[2][1].canHaveCard(), true);
      Assert.assertEquals(cell2darr[2][2].canHaveCard(), false);
      Assert.assertEquals(cell2darr[3][0].canHaveCard(), false);
      Assert.assertEquals(cell2darr[3][1].canHaveCard(), true);
      Assert.assertEquals(cell2darr[3][2].canHaveCard(), true);
    } catch (FileNotFoundException ex) {
      Assert.fail(ex.getMessage());
    }

  }

}
