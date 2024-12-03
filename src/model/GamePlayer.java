package model;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a player in the game(Human or AI) which interacts with the game model and
 * complete moves based on the current game state.
 * <p>
 *   Extends {@link BiConsumer}, where the first param is a {@link Consumer} that
 *   accepts a move chosen by the player, and the second param is a {@link Supplier}
 *   that gives access to a model representing the current state of the game.
 * </p>
 */
public interface GamePlayer extends BiConsumer<Consumer<Move>,
    Supplier<Model>> {

  /**
   * Handles the interaction between the player and the game.
   * The player uses a {@link Supplier} of the model to access the game state and
   * completes their {@link Move} through the {@link Consumer}.
   *
   * @param moveConsumer accepts the player's chosen move
   * @param supplier provides the current game state
   */
  @Override
  void accept(Consumer<Move> moveConsumer, Supplier<Model> supplier);

}
