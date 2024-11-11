package cs3500.threetrios.player;

import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.Model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class StrategyAbstract {
  protected Supplier<Model> modelSupplier;

  public StrategyAbstract(Supplier<Model> modelSupplier) {
    this.modelSupplier = modelSupplier;
  }

  /**
   * to find all possible moves
   *
   * @return
   */
  protected abstract List<Consumer<Model>> allConsideredMoves();

  /**
   * find effectiveness by comparing the model's state from this.modelSupplier
   * and the state after applying the move
   *
   * @param move - a consumer of the model
   * @return - an int rating of the effectiveness
   */
  protected abstract int effectiveness(Consumer<Model> move);

  /**
   * to find the best move by reducing all possible moves, choosing the most effective
   * via this.effectiveness
   *
   * @return - the best move, if it exists
   */
  protected final Optional<Consumer<Model>> bestMove() {
    return allConsideredMoves().stream()
            .reduce(BinaryOperator.maxBy(Comparator.comparingInt(this::effectiveness)));
  }

  protected final List<GridCellReadOnly> modelToCellList(Model model) {
    return Arrays.stream(model.getGrid().readOnly2dCellArray()).flatMap(Arrays::stream).collect(
            Collectors.toList());
  }
}
