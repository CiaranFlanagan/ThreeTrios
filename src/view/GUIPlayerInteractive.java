package view;

import model.Move;
import model.CoachColor;
import model.Model;
import utils.TriConsumer;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GUIPlayerInteractive extends GUIPlayerDelegate implements
    BiConsumer<Consumer<Move>, Callable<Model>> {

  protected TriConsumer<Move, Consumer<Move>, BiConsumer<Move, Consumer<Move>>> hand;
  protected BiConsumer<Move, Consumer<Move>> grid;
  protected Consumer<Move> callback;

  public GUIPlayerInteractive(GUIHandInteractive viewRedHand,
                              GUIHandBase viewBlueHand,
                              GUIGridInteractive viewGrid) {
    super(viewRedHand, viewBlueHand, viewGrid, CoachColor.RED, null);
    hand = viewRedHand;
    grid = viewGrid;
    updateDelegate();
  }

  private void updateDelegate() {
    super.delegate = this;
  }

  public GUIPlayerInteractive(GUIHandBase viewRedHand,
                              GUIHandInteractive viewBlueHand,
                              GUIGridInteractive viewGrid) {
    super(viewRedHand, viewBlueHand, viewGrid, CoachColor.BLUE, null);
    hand = viewBlueHand;
    grid = viewGrid;
    updateDelegate();
  }

  @Override
  public void accept(Consumer<Move> callback, Callable<Model> modelCallable) {
    this.callback = callback;
    super.accept(this :: onMove, modelCallable);

  }

  private void onMove(Move move) {
    Move newMove = Move.create();
    hand.accept(newMove, callback, grid);
  }

}
