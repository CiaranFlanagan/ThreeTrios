package controller;


import model.CoachColor;
import model.GamePlayer;
import model.Model;
import model.Move;
import view.GameView;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a player in the game of Three Trios. A player is a listener of a callable
 * model and also calls back what to do with a move.
 */
public class ControlPlayer extends AbstractControlPlayer {

  /**
   * Constructor.
   * @param color  color of the player
   * @param view   view of the game
   * @param player player of the game
   */
  public ControlPlayer(CoachColor color, GameView view, GamePlayer player) {
    super(color, view, player);
  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Callable<Model> modelCallable) {
    Supplier<Model> modelSupplier = () -> {
      try {
        return modelCallable.call();
      } catch (Exception e) {
        view.renderMessage(e.getMessage());
        return null;
      }
    };

    if (modelSupplier.get() != null) {
      view.renderModel(modelSupplier.get());
      if (!modelSupplier.get().isGameOver()) {
        player.accept(moveConsumer, modelSupplier);
      }
    }

  }

}
