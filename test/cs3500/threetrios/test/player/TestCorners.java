package cs3500.threetrios.test.player;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.player.CornerStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * To test the corner strategy via properties and mocks to make sure that we handle common errors.
 * <p>
 * What are some common errors/easy requirements specifically for corners?
 *
 * 1. You consider some cell other than a corner.
 *
 * 2.
 *
 * 3. c
 *
 */
public class TestCorners extends TestStrategy {

  @Override
  public void setStrategy() {
    super.setStrategy();
    strategy = new CornerStrategy(modelSupplier);
  }

  @Test
  public void testLogObservations() {
    List<List<Integer>> log = new ArrayList<>();
    Model model = new MockStrategyLogMoves(log);
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.GRID_ASSN_HARD),
                    ConfigCard.scannerToCardList(TestFiles.CC_LARGE),
                    new RefereeDefault());
    modelSupplier = () -> model;
    setStrategy();
    strategy.bestMove();
    System.out.println(model.numRows());
    System.out.println(model.numCols());
    for (List<Integer> rowColPair : log) {
      int row = rowColPair.get(0);
      int col = rowColPair.get(1);
      Assert.assertTrue(row == 0 || row == model.numRows() - 1);
      Assert.assertTrue(col == 0 || col == model.numCols() - 1);
    }

  }

}
