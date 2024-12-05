package view;

import model.Move;
import provider.Features;
import provider.ReadOnlyGameModel;
import provider.ThreeTriosViewImpl;

/**
 * Adapts our providers view into a view usable by our implementation.
 */
public class InteractiveViewAdapter implements
    model.GamePlayer,
    GameView,
    Features {

  // mvc
  private final model.SettableAdaptedModel model = new model.SettableAdaptedModel();
  private final model.Move move = Move.create();
  private java.util.function.Consumer<model.Move> callback = m -> {
  };

  // adapt
  java.util.function.Function<ReadOnlyGameModel,
      ThreeTriosViewImpl>
      delegateMaker;

  ThreeTriosViewImpl delegate;

  public InteractiveViewAdapter(java.util.function.Function<ReadOnlyGameModel,
      ThreeTriosViewImpl> delegateMaker) {
    this.delegateMaker = delegateMaker;
  }

  @Override
  public void accept(java.util.function.Consumer<model.Move> moveConsumer,
                     java.util.function.Supplier<model.Model> supplier) {
    if (delegate == null) {
      delegate = delegateMaker.apply(model);
      delegate.setFeatures(this);
    }
    model.set(new model.ModelForView(supplier.get()));
    callback = moveConsumer;
  }

  @Override
  public void handleCellClick(int row, int col) {
    move.row = row;
    move.col = col;
    callback.accept(move);
  }

  @Override
  public void handleAIMove() {
    // no-op
  }

  @Override
  public void handleCardSelection(int cardIndex) {
    move.handIdx = cardIndex;
  }

  @Override
  public void renderMessage(String message) {
    utils.Utils.popup(message, delegate);
  }

  @Override
  public void renderModel(model.ModelReadOnly model) {
    if (delegate == null) {
      delegate = delegateMaker.apply(this.model.set(model));
      delegate.setFeatures(this);
    }
    delegate.refresh();
  }

}
