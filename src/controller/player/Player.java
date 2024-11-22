package controller.player;

import model.CoachColor;
import model.Model;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a player in the game of Three Trios.
 */
public final class Player implements BiConsumer<Consumer<Move>, Supplier<Model>> {
  CoachColor coachColor;
  BiConsumer<Consumer<Move>, Supplier<Model>> delegate;

  public Player (CoachColor coachColor,
                 BiConsumer<Consumer<Move>, Supplier<Model>> delegate) {
    this.coachColor = coachColor;
    this.delegate = delegate;
  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Supplier<Model> modelSupplier) {
    delegate.accept(moveConsumer, modelSupplier);
  }

  @Override
  public String toString() {
    return coachColor.toString();
  }

}
