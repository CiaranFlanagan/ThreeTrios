package test;

import model.Card;
import model.Grid;
import model.GridCellReadOnly;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import utils.ConfigCard;
import utils.ConfigGrid;
import utils.LineWriter;
import utils.TestFiles;

import java.util.List;

/**
 * Tests for the ThreeTrios controller
 */
@RunWith(Enclosed.class)
public class TestController {

  /**
   * Tests for the BoardConfig class.
   */
  public static class TestBoardConfig {

    @Test
    public void testFullVaried() {
      Grid grid = ConfigGrid.scannerToGrid(TestFiles.GRID_DISC_HOLES);
      // CCC
      // XXX
      // CCC
      // CXX
      GridCellReadOnly[][] cell2darr = grid.readOnlyArray2D();
      Assert.assertEquals(cell2darr[0][0].canHaveCard(), true);
      Assert.assertEquals(cell2darr[0][1].canHaveCard(), true);
      Assert.assertEquals(cell2darr[0][2].canHaveCard(), true);
      Assert.assertEquals(cell2darr[1][0].canHaveCard(), false);
      Assert.assertEquals(cell2darr[1][1].canHaveCard(), false);
      Assert.assertEquals(cell2darr[1][2].canHaveCard(), false);
      Assert.assertEquals(cell2darr[2][0].canHaveCard(), true);
      Assert.assertEquals(cell2darr[2][1].canHaveCard(), true);
      Assert.assertEquals(cell2darr[2][2].canHaveCard(), true);
      Assert.assertEquals(cell2darr[3][0].canHaveCard(), true);
      Assert.assertEquals(cell2darr[3][1].canHaveCard(), false);
      Assert.assertEquals(cell2darr[3][2].canHaveCard(), false);

    }

  }

  /**
   * Tests for the CardConfig class.
   */
  public static class TestCardConfig {

    @Test
    public void test1() {
      List<Card> cards = ConfigCard.scannerToCardList(TestFiles.CC_SMALL);
      //<TTCard: bob 1 2 3 A>, <TTCard: kc A 4 7 9>, <TTCard: ciaran 1 2 3 4>
      String expected = LineWriter.create()
                                  .endWith(
                                      "[bob 1 1 1 1, " + "kc 5 5 5 5, " + "zeke A A A A, "
                                          + "ciaran 1 1 1 1]")
                                  .toString();
      Assert.assertEquals(expected, cards.toString());
    }


  }

}
