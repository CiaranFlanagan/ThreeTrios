package view;

import java.util.function.Consumer;
import java.util.function.Supplier;

import model.CoachColor;
import model.GamePlayer;
import model.Model;
import model.ModelReadOnly;
import model.Move;

public class GUIPlayerAssn9 implements GameView, GamePlayer {
  protected GUIPlayerInteractive delegate;

  protected GuiGridShowHints grid;

  public GUIPlayerAssn9(CoachColor color) {
    grid = new GuiGridShowHints(new DrawGridShowHints());
    if (color == CoachColor.RED) {
      delegate = new GUIPlayerInteractive(new GUIHandInteractive(new DrawHand()),
          new GUIHandBase(new DrawHand()), grid);
    } else if (color == CoachColor.BLUE) {
      delegate = new GUIPlayerInteractive(
          new GUIHandBase(new DrawHand()), new GUIHandInteractive(new DrawHand()),
          grid);
    }
  }

  /**
   * Handles the interaction between the player and the game.
   * The player uses a {@link Supplier} of the model to access the game state and
   * completes their {@link Move} through the {@link Consumer}.
   *
   * @param moveConsumer accepts the player's chosen move
   * @param supplier     provides the current game state
   */
  @Override
  public void accept(Consumer<Move> moveConsumer, Supplier<Model> supplier) {
    grid.setModel(supplier.get());
    delegate.accept(moveConsumer, supplier);
  }

  @Override
  public void renderMessage(String message) {
    delegate.renderMessage(message);
  }

  @Override
  public void renderModel(ModelReadOnly model) {
    delegate.renderModel(model);
  }
}
