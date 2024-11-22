package controller.player;

import model.CoachColor;
import model.Model;
import model.Move;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Represents a player in the game of Three Trios. A player is an observer of
 * Callable<Model> and it also
 */
public final class Player implements BiConsumer<Consumer<Move>, Callable<Model>> {
  CoachColor coachColor;
  BiConsumer<Consumer<Move>, Callable<Model>> delegate;

  public Player (CoachColor coachColor,
                 BiConsumer<Consumer<Move>, Callable<Model>> delegate) {
    this.coachColor = coachColor;
    this.delegate = delegate;
  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Callable<Model> modelSupplier) {
    delegate.accept(moveConsumer, modelSupplier);
  }

  @Override
  public String toString() {
    return coachColor.toString();
  }

}
