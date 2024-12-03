package test;

import controller.ControlPlayer;
import controller.strategy.StrategyAbstract;
import model.Card;
import model.CoachColor;
import model.GameListener;
import model.Grid;
import model.GridCellReadOnly;
import model.Model;
import model.ModelReadOnly;
import model.Move;
import model.PlayableGame;
import model.Referee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import utils.ConfigCard;
import utils.ConfigGrid;
import utils.LineWriter;
import utils.TestFiles;
import view.GameView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Tests for the ThreeTrios controller.
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

  /**
   * Tests for the controller implementation.
   */
  public static class TestControllerImpl {
    // test that error is given to view
    // test that player doesn't get a null or game with game over


    @Test
    public void testError() {
      try {
        String[] stringBox = new String[1];
        new PlayableGameAlwaysErr().start(() -> null,
                                          new ControlPlayer(CoachColor.RED,
                                                            new MockView(stringBox
                                                                , null),
                                                            null), null);
        // make sure that the view gets the error message correctly
        // if the player is even called, this should throw a null pointer because
        // we shouldn't even contact the player.
        Assert.assertNotNull(stringBox[0]);
      } catch (Exception e) {
        Assert.fail("mishandled exception");
      }


    }

    @Test
    public void testGameOver() {
      try {
        String[] stringBox = new String[1];
        boolean[] booleanBox = new boolean[1];
        new PlayableGameAlwaysOver().start(() -> null,
                                          new ControlPlayer(CoachColor.RED,
                                                            new MockView(stringBox
                                                                , null),
                                                            new FakeStrategy(booleanBox)),
                                          null);
        // player should not be told to work if the game is over.
        // controller should notice that first.
        Assert.assertFalse(booleanBox[0]);
      } catch (Exception e) {
        Assert.fail("mishandled exception");
      }

    }


    /**
     * Mock for the view.
     */
    private static class MockView implements GameView {

      private String[] stringBox;
      private boolean[] booleanBox;

      MockView(String[] stringBox, boolean[] booleanBox) {
        this.stringBox = stringBox;
      }

      @Override
      public void renderMessage(String message) {
        stringBox[0] = message;
      }

      @Override
      public void renderModel(ModelReadOnly model) {
        booleanBox[0] = true;
      }

    }


    /**
     * Game is always over.
     */
    private static class PlayableGameAlwaysOver implements PlayableGame {

      @Override
      public void start(Supplier<Model> modelSupplier,
                        GameListener red,
                        GameListener blue) {
        red.accept(m -> {
        }, GameOver :: new);
      }

    }

    /**
     * Game always errors.
     */
    private static class PlayableGameAlwaysErr implements PlayableGame {

      @Override
      public void start(Supplier<Model> modelSupplier,
                        GameListener red,
                        GameListener blue) {
        red.accept(m -> {
        }, () -> {
            throw new RuntimeException("ahh");
          });
      }

    }

  }

  /**
   * A fake strategy.
   */
  private static class FakeStrategy extends StrategyAbstract {

    private boolean[] box;

    FakeStrategy(boolean[] box) {
      this.box = box;
    }

    @Override
    protected int effectiveness(Move move, Supplier<Model> modelSupplier) {
      box[0] = true;
      return 0;
    }

  }


  /**
   * Mock for the model.
   */
  private static class GameOver implements Model {

    @Override
    public void startGame(Grid grid, List<Card> cards, Referee referee) {
      // Do nothing.
    }

    @Override
    public void placeCard(int idx, int row, int col) throws IllegalStateException,
        IllegalArgumentException {
      // Do nothing.
    }

    @Override
    public CoachColor curCoach() {
      return null;
    }

    @Override
    public Map<CoachColor, List<Card>> curCoachesHands() {
      return Map.of();
    }

    @Override
    public boolean isGameStarted() {
      return false;
    }

    @Override
    public boolean isGameOver() {
      return true;
    }

    @Override
    public CoachColor winner() {
      return null;
    }

    @Override
    public Grid curGrid() {
      return null;
    }

    @Override
    public int numRows() {
      return 0;
    }

    @Override
    public int numCols() {
      return 0;
    }

    @Override
    public Optional<Card> cardAt(int row, int col) {
      return Optional.empty();
    }

    @Override
    public Optional<CoachColor> ownerAt(int row, int col) {
      return Optional.empty();
    }

    @Override
    public boolean canPlayAt(int row, int col) {
      return false;
    }

    @Override
    public int numFlippedIfPlaced(Card card, int row, int col) {
      return 0;
    }

    @Override
    public int score(CoachColor coach) {
      return 0;
    }

  }

}
