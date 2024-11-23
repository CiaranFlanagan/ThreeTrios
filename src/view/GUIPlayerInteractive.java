package view;

import model.CoachColor;
import model.Model;
import model.Move;
import utils.TriConsumer;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * To represent a player in the game of three trios as a gui and also handle
 * interactions accordingly.
 */
public class GUIPlayerInteractive extends GUIPlayerDelegate implements
    BiConsumer<Consumer<Move>, Callable<Model>> {

  protected TriConsumer<Move, Consumer<Move>, BiConsumer<Move, Consumer<Move>>> hand;
  protected BiConsumer<Move, Consumer<Move>> grid;
  protected Consumer<Move> callback;

  /**
   * Constructor.
   * @param redHand an interactive red hand
   * @param blueHand a base blue hand
   * @param grid an interactive grid
   */
  public GUIPlayerInteractive(GUIHandInteractive redHand,
                              GUIHandBase blueHand,
                              GUIGridInteractive grid) {
    super(redHand, blueHand, grid, CoachColor.RED, null);
    hand = redHand;
    this.grid = grid;
  }

  /**
   * Constructor.
   * @param redHand  a base red hand
   * @param blueHand an interactive blue hand
   * @param grid     an interactive grid
   */
  public GUIPlayerInteractive(GUIHandBase redHand,
                              GUIHandInteractive blueHand,
                              GUIGridInteractive grid) {
    super(redHand, blueHand, grid, CoachColor.BLUE, null);
    hand = blueHand;
    this.grid = grid;
  }

  @Override
  public void accept(Consumer<Move> callback, Callable<Model> modelCallable) {
    if (!propagateCallable(modelCallable)) {
      Move newMove = Move.create();
      hand.accept(newMove, callback, grid);
    }
  }

}
