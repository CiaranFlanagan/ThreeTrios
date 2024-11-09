package cs3500.threetrios.player;

import cs3500.threetrios.model.Model;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Strategy 1 - the strategy that finds the move that flips the most cards.
 */
public class MostFlips extends StrategyAbstract<MostFlips> {

  /**
   * @param modelSupplier - a supplier of a model, assumes not null
   * @return -
   */
  @Override
  public int effectiveness(Supplier<Model> modelSupplier) {
    Model model = modelSupplier.get();
    if (validMove(this, model)) {
      // TODO
      return 0;
    } else {
      return -1;
    }
  }

  /**
   * @param modelSupplier - a supplier of a model
   * @return - the best move if it exists
   */
  @Override
  public Optional<Consumer<Model>> computeBestNextMove(Supplier<Model> modelSupplier) {
    return Optional.empty();
  }

}
