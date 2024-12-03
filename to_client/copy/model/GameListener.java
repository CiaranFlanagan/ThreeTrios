package copy.model;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Represents a listener for a playable game, capable of processing moves
 * and allows accessing the current game state.
 */
public interface GameListener extends
    BiConsumer<Consumer<Move>, Callable<Model>> {

  /**
   * Processes a move and allows access to the current game state.
   *
   * @param moveConsumer accepts a move to be applied
   * @param modelCallable provides the current game state when called
   */
  @Override
  void accept(Consumer<Move> moveConsumer, Callable<Model> modelCallable);

}
