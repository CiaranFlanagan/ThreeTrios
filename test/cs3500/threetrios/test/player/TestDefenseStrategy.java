package cs3500.threetrios.test.player;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.player.DefenseStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Tests the extra credit defense start
 */
public class TestDefenseStrategy extends TestStrategy {
  @Test
  public void testCardsAreLogged() {
    List<List<Integer>> log = new ArrayList<>();
    Supplier<Model> modelSupplier = () -> {
      Model mock = new MockStrategyLogMoves(log);
      Grid grid =
          ConfigGrid.scannerToGrid(TestFiles.GRID_NO_HOLES_THREE_BY_THREE);
      List<Card> cards =
          ConfigCard.scannerToCardList(TestFiles.CC_LARGE);
      mock.startGame(grid, cards, new RefereeDefault());
      return mock;
    };
    DefenseStrategy strategy = new DefenseStrategy(() -> modelSupplier.get());
    strategy.bestMove();
    System.out.println(log);
    Assert.assertFalse(log.isEmpty());
  }

  //tests that the number of cells visited is equal to the number of cells in the grid.
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
    //List<List<Integer>> seen = new List<List<Integer>>() {
    for (List<Integer> rowColPair : log) {
      int row = rowColPair.get(0);
      int col = rowColPair.get(1);
      Assert.assertTrue(row == 0 || row == model.numRows() - 1);
      Assert.assertTrue(col == 0 || col == model.numCols() - 1);
    }

  }


}
