package cs3500.threetrios.test.player;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.ModelBase;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.player.CornerStrategy;
import cs3500.threetrios.model.player.Move;
import cs3500.threetrios.model.player.StrategyAbstract;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * To test requirements for every player strategy.
 * <p>
 * What requirements?
 * <p>
 * 1. Bias top left.
 * <p>
 * 2. Default move to up-most, left-most, with leftmost card.
 */
public class TestStrategy {

  protected StrategyAbstract strategy;
  protected Optional<Move> move;
  protected Supplier<Model> modelSupplier;

  /**
   * meant to be overridden so that strategy is updated after model Supplier is made.
   */
  public void setStrategy() {

  }

  @Test
  public void testTopLeftBias() {
    if (move.isEmpty()) {
      Assert.fail("strategy should probably produce a move on the first move.");
    }
    // What does our model need to do? It just has to be freshly started.
    modelSupplier = new NewGameModelSupplier();
    setStrategy();
    Assert.assertEquals(move.get().getRow(), 0);
    Assert.assertEquals(move.get().getCol(), 0);
  }

  @Test
  public final void testMoveOnFail() {
    // it's public final so we can just test this for a known implementation
    // if we know a corner strategy needs corners, let's just fill the corners...
    modelSupplier = new AllCornersFilledModelSupplier();
    strategy = new CornerStrategy(modelSupplier);
    System.out.println(strategy.bestMove().get());
    Assert.assertTrue(strategy.bestMove().isEmpty());
    Assert.assertEquals(strategy.defaultMove(), Move.create(0, 0, 0));
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


