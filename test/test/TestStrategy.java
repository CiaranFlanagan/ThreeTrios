package test;

import controller.strategy.CornerStrategy;
import controller.strategy.DefenseStrategy;
import controller.strategy.MostFlips;
import controller.strategy.StrategyAbstract;
import model.Card;
import model.CoachColor;
import model.Grid;
import model.Model;
import model.ModelBase;
import model.Move;
import model.RefereeDefault;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import utils.ConfigCard;
import utils.ConfigGrid;
import utils.LineWriter;
import utils.TestFiles;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * To test requirements for every player strategy, like whether they bias the top left, have a
 * default move, can produce no result, and that they check the correct cells.
 */

@RunWith(Enclosed.class)
public class TestStrategy {


  /**
   * A test class for the MostFlips strategy in the Three Trios game. It verifies that the
   * strategy selects moves that flip the maximum number of opponent's cards.
   * The test case is structured to check different grid configurations and move scenarios.
   */
  public static class TestMostFlips {
    // cases we want to test:
    // 1. empty grid
    // 2. case where one position flips the most cards
    // 3. case where two positions flip the most cards (pick top left)

    // constructing a good test case to meet this criteria
    // empty 2x2
    // place red  1 1 1 1 at 0, 0
    // place blue 2 2 2 2 at 0, 1
    // place red  3 3 3 3 at 1, 0

    // what observations do we care about. we want to take a model a see what a card's attack values
    // and colors at a particular position

    @Test
    public void bigTestFullGame() {
      Supplier<Grid> gridSupplier = () -> ConfigGrid.scannerToGrid(new Scanner(LineWriter.create()
                                                                                         .line("2 3")
                                                                                         .line("CCX")
                                                                                         .endWith(
                                                                                             "CCC")
                                                                                         .toString()));
      Supplier<List<Card>> cardSupplier =
          () -> ConfigCard.scannerToCardList(new Scanner(LineWriter.create()
                                                                   .line("a 1 1 1 1")
                                                                   .line("b 2 2 2 2")
                                                                   .line("c 3 3 3 3")
                                                                   .line("d 4 4 4 4")
                                                                   .line("e 5 5 5 5")
                                                                   .endWith("f 5 5 5 5")
                                                                   .toString()));
      Supplier<Model> modelSupplier = () -> {
        Model m = new ModelBase();
        m.startGame(gridSupplier.get(), cardSupplier.get(), new RefereeDefault());
        return m;
      };

      // move 1
      MostFlips mostFlips = new MostFlips();
      Optional<Move> move1 = mostFlips.bestMove(modelSupplier);
      Supplier<Model> modelSupplier1 = () -> {
        Model m = modelSupplier.get();
        move1.get().accept(m);
        return m;
      };
      Assert.assertTrue(utils.Utils.cardAt(modelSupplier1.get(), 0, 0).isPresent());
      Assert.assertEquals(utils.Utils.cardAt(modelSupplier1.get(), 0, 0).get().getCoach(),
                          CoachColor.RED);
      Assert.assertEquals(utils.Utils.cardAt(modelSupplier1.get(), 0, 0).get().toString(),
                          "a 1 1 1 1");

      // move 2
      mostFlips = new MostFlips();
      Optional<Move> move2 = mostFlips.bestMove(modelSupplier1);
      Supplier<Model> modelSupplier2 = () -> {
        Model m = modelSupplier1.get();
        move2.get().accept(m);
        return m;
      };
      Assert.assertTrue(utils.Utils.cardAt(modelSupplier2.get(), 0, 1).isPresent());
      Assert.assertEquals(utils.Utils.cardAt(modelSupplier2.get(), 0, 0).get().getCoach(),
                          CoachColor.BLUE);
      Assert.assertEquals(utils.Utils.cardAt(modelSupplier2.get(), 0, 1).get().getCoach(),
                          CoachColor.BLUE);
      Assert.assertEquals(utils.Utils.cardAt(modelSupplier2.get(), 0, 1).get().toString(),
                          "b 2 2 2 2");

      // move 3
      mostFlips = new MostFlips();
      Optional<Move> move3 = mostFlips.bestMove(modelSupplier2);
      Supplier<Model> modelSupplier3 = () -> {
        Model m = modelSupplier2.get();
        move3.get().accept(m);
        return m;
      };
      Assert.assertTrue(utils.Utils.cardAt(modelSupplier3.get(), 1, 1).isPresent());
      Assert.assertEquals(utils.Utils.cardAt(modelSupplier3.get(), 0, 0).get().getCoach(),
                          CoachColor.RED);
      Assert.assertEquals(utils.Utils.cardAt(modelSupplier3.get(), 0, 1).get().getCoach(),
                          CoachColor.RED);
      Assert.assertEquals(utils.Utils.cardAt(modelSupplier3.get(), 1, 1).get().getCoach(),
                          CoachColor.RED);
      Assert.assertEquals(Utils.cardAt(modelSupplier3.get(), 1, 1).get().toString(),
                          "c 3 3 3 3");
    }

    @Test
    public void testLog() {
      List<List<Integer>> log = new ArrayList<>();
      Supplier<Model> modelSupplier = () -> {
        Model mock = new MockStrategyLogMoves(log);
        Grid grid = ConfigGrid.scannerToGrid(TestFiles.GRID_NO_HOLES_THREE_BY_THREE);
        List<Card> cards = ConfigCard.scannerToCardList(TestFiles.CC_LARGE);
        mock.startGame(grid, cards, new RefereeDefault());
        return mock;
      };
      MostFlips strategy = new MostFlips();
      strategy.bestMove(modelSupplier);
      Assert.assertFalse(log.isEmpty());
    }

  }

  /**
   * To test the corner strategy via properties and mocks to make sure that we handle
   * common errors.
   */
  public static class TestCornerStrategy {

    private StrategyAbstract strategy;
    private Optional<Move> move;
    private Supplier<Model> modelSupplier;

    @Test
    public void testTopLeftBias() {
      // What does our model need to do? It just has to be freshly started.
      modelSupplier = new NewGameModelSupplier();
      strategy = new CornerStrategy();
      move = Optional.of(strategy.defaultMove(modelSupplier));
      Assert.assertEquals(move.get().row, 0);
      Assert.assertEquals(move.get().col, 0);
    }


    @Test
    public void testMoveOnFail() {
      // it's public final so we can just test this for a known implementation
      // if we know a corner strategy needs corners, let's just fill the corners...
      modelSupplier = new AllCornersFilledModelSupplier();
      strategy = new CornerStrategy();
      Assert.assertTrue(strategy.bestMove(modelSupplier).isEmpty());
      //Assert.assertTrue(strategy.bestMove().isEmpty());
      // 0, 1 because up-most then left-most
      Assert.assertEquals(strategy.defaultMove(modelSupplier), Move.create(0, 0, 1));
    }

    @Test
    public void testLogObservations() {
      List<List<Integer>> log = new ArrayList<>();
      Model model = new MockStrategyLogMoves(log);
      model.startGame(ConfigGrid.scannerToGrid(TestFiles.GRID_ASSN_HARD),
                      ConfigCard.scannerToCardList(TestFiles.CC_LARGE),
                      new RefereeDefault());
      modelSupplier = () -> model;
      strategy = new CornerStrategy();
      strategy.bestMove(modelSupplier);
      for (List<Integer> rowColPair : log) {
        int row = rowColPair.get(0);
        int col = rowColPair.get(1);
        Assert.assertTrue(row == 0 || row == model.numRows() - 1);
        Assert.assertTrue(col == 0 || col == model.numCols() - 1);
      }

    }

  }


  /**
   * Tests the extra credit defense start
   */
  public static class TestDefenseStrategy {

    private StrategyAbstract strategy;
    private Optional<Move> move;
    private Supplier<Model> modelSupplier;

    public void testTopLeftBias() {
      if (move.isEmpty()) {
        Assert.fail("strategy should probably produce a move on the first move.");
      }
      // What does our model need to do? It just has to be freshly started.
      modelSupplier = new NewGameModelSupplier();
      strategy = new DefenseStrategy();
      Assert.assertEquals(move.get().row, 0);
      Assert.assertEquals(move.get().col, 0);
    }

    @Test
    public void testCardsAreLogged() {
      List<List<Integer>> log = new ArrayList<>();
      Supplier<Model> modelSupplier = () -> {
        Model mock = new MockStrategyLogMoves(log);
        Grid grid = ConfigGrid.scannerToGrid(TestFiles.GRID_NO_HOLES_THREE_BY_THREE);
        List<Card> cards = ConfigCard.scannerToCardList(TestFiles.CC_LARGE);
        mock.startGame(grid, cards, new RefereeDefault());
        return mock;
      };
      DefenseStrategy strategy = new DefenseStrategy();
      strategy.bestMove(modelSupplier);
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
      strategy = new DefenseStrategy();
      strategy.bestMove(modelSupplier);
      List<List<Integer>> seen = new ArrayList<>();
      for (List<Integer> rowColPair : log) {
        if (!seen.contains(rowColPair)) {
          seen.add(rowColPair);
        }
      }
      Assert.assertEquals(seen.size(), model.numCols() * model.numRows());

    }


  }

  /**
   * A mock implementation of the ModelBase class used for testing strategies in the Three
   * Trios game. MockStrategyLogMoves logs card placements instead of interacting with an
   * actual game grid, allowing verification of method calls and parameters during
   * testing.
   */
  private static class MockStrategyLogMoves extends ModelBase {

    List<List<Integer>> log;

    /**
     * Constructs a MockMostFlips with a specified log to track the row and column
     * coordinates of placed cards.
     * @param log a list of lists used to record each placement's row and column values
     */
    public MockStrategyLogMoves(List<List<Integer>> log) {
      this.log = log;
    }

    @Override
    public void placeCard(int idx, int row, int col) throws IllegalStateException,
        IllegalArgumentException {
      log.add(List.of(row, col));
    }

  }

  /**
   * Creates a new fresh game.
   */
  private static class NewGameModelSupplier implements Supplier<Model> {

    @Override
    public Model get() {
      Model model = new ModelBase();
      model.startGame(ConfigGrid.scannerToGrid(TestFiles.GRID_ASSN_HARD),
                      ConfigCard.scannerToCardList(TestFiles.CC_LARGE),
                      new RefereeDefault());
      return model;
    }

  }

  /**
   * Creates a game with all corneres filled.
   */
  private static class AllCornersFilledModelSupplier implements Supplier<Model> {

    @Override
    public Model get() {
      Model model = new ModelBase();
      model.startGame(ConfigGrid.scannerToGrid(TestFiles.GRID_ASSN_HARD),
                      ConfigCard.scannerToCardList(TestFiles.CC_LARGE),
                      new RefereeDefault());
      model.placeCard(0, 0, 0);
      model.placeCard(0, model.numRows() - 1, 0);
      model.placeCard(0, 0, model.numCols() - 1);
      model.placeCard(0, model.numRows() - 1, model.numCols() - 1);
      return model;
    }

  }

}
