package cs3500.threetrios.player;

import cs3500.threetrios.model.Model;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @param <S> - child of strategy
 */
public interface Strategy<S extends Strategy<S>> extends Consumer<Model> {

  /**
   * to find the arbitrary weighted effectiveness of a single move at a single place.
   * @param modelSupplier - a supplier of a model
   * @return - the effectiveness of this strategy.
   */
  int effectiveness(Supplier<Model> modelSupplier);

  /**
   * utilize betterOf to compare multiple strategies via their effectiveness.
   * @param modelSupplier - a supplier of a model
   * @return - the best move if it exists computed by this strategy.
   */
  default Optional<Consumer<Model>> computeBestNextMove(Supplier<Model> modelSupplier) {
    // TODO
    return Optional.empty();
  }

}