package view;

import controller.player.Move;
import model.CoachColor;
import model.Model;
import utils.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GUIPlayerInteractive extends GUIPlayerBase implements
    BiConsumer<Consumer<Move>, Supplier<Model>> {

  protected TriConsumer<Move, Consumer<Move>, BiConsumer<Move, Consumer<Move>>> hand;
  protected BiConsumer<Move, Consumer<Move>> grid;

  public GUIPlayerInteractive(GUIHandInteractive viewRedHand,
                              GUIHandBase viewBlueHand,
                              GUIGridInteractive viewGrid) {
    super(viewRedHand, viewBlueHand, viewGrid, CoachColor.RED);
    hand = viewRedHand;
    grid = viewGrid;
  }

  public GUIPlayerInteractive(GUIHandBase viewRedHand,
                              GUIHandInteractive viewBlueHand,
                              GUIGridInteractive viewGrid) {
    super(viewRedHand, viewBlueHand, viewGrid, CoachColor.BLUE);
    hand = viewBlueHand;
    grid = viewGrid;
  }

  @Override
  public void accept(Consumer<Move> callback, Supplier<Model> modelSupplier) {
    model = modelSupplier.get();
    update(model);
    Move newMove = Move.create();
    hand.accept(newMove, callback, grid);
  }

}
