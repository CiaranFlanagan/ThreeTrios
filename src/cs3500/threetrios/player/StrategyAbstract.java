package cs3500.threetrios.player;

import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class StrategyAbstract {
  protected Supplier<Model> modelSupplier;

  public StrategyAbstract(Supplier<Model> modelSupplier) {
    this.modelSupplier = modelSupplier;
  }

  /**
   * to find all possible moves where you play and it doesn't error
   *
   * @return
   */
  protected List<Move> allConsideredMoves() {
    Model model = modelSupplier.get();
    int numRows = model.getGrid().readOnly2dCellArray().length;
    int numColumns = model.getGrid().readOnly2dCellArray()[0].length;
    int sizeOfHand = model.getCurrentCoach().getHand().size();
    List<Move> acc0 = new ArrayList<>();
    IntStream.range(0, numRows).forEach(
            (row) -> IntStream.range(0, numColumns).forEach(
                    (col) -> IntStream.range(0, sizeOfHand).forEach(
                            (id) -> acc0.add(Move.of(row, col, id)))));
    return acc0.stream().filter((m) ->
                                {
                                  try {
                                    m.accept(modelSupplier.get());
                                    return true;
                                  } catch (Exception e) {
                                    return false;
                                  }
                                }).collect(Collectors.toList());
  }

  /**
   * find effectiveness by comparing the model's state from this.modelSupplier
   * and the state after applying the move
   *
   * @param move - a consumer of the model
   * @return - an int rating of the effectiveness
   */
  protected abstract int effectiveness(Move move);

  /**
   * to find the best move by reducing all possible moves, choosing the most effective
   * via this.effectiveness. works directly with effectiveness
   *
   * @return - the best move, if it exists
   */
  public Optional<Move> bestMove() {
    return allConsideredMoves().stream()
            .reduce(BinaryOperator.maxBy(Comparator.comparingInt(this::effectiveness)));
  }

  protected final List<GridCellReadOnly> modelToCellList(Model model) {
    return Arrays.stream(model.getGrid().readOnly2dCellArray()).flatMap(Arrays::stream).collect(
            Collectors.toList());
  }
}
