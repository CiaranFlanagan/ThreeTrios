package cs3500.threetrios.model.player;

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

/**
 * Abstract base class for game strategies in the Three Trios game.
 * Provides methods to evaluate possible moves and determine the most effective move based on
 * specific criteria defined in subclasses.
 */
public abstract class StrategyAbstract {
  protected Supplier<Model> modelSupplier;

  /**
   * Constructs a StrategyAbstract with a supplier for the game model.
   *
   * @param modelSupplier a Supplier for the game Model, providing access to the current game state
   */
  public StrategyAbstract(Supplier<Model> modelSupplier) {
    this.modelSupplier = modelSupplier;
  }

  /**
   * Generates all possible moves for the current player that do not result in errors.
   * Each move places a card from the player's hand at a specific row and column on the grid.
   *
   * @return a list of valid Move objects that the current player can make
   */
  protected List<Move> allConsideredMoves() {
    Model model = modelSupplier.get();
    int numRows = model.curGrid().readOnlyArray2D().length;
    int numColumns = model.curGrid().readOnlyArray2D()[0].length;
    int sizeOfHand = model.curCoachesHands().get(model.curCoach()).size();
    List<Move> acc0 = new ArrayList<>();
    IntStream.range(0, numRows).forEach(
        (row) -> IntStream.range(0, numColumns).forEach(
            (col) -> IntStream.range(0, sizeOfHand).forEach(
                (id) -> acc0.add(Move.create(row, col, id)))));
    return filterOutIllegalMoves(acc0);
  }

  protected List<Move> filterOutIllegalMoves(List<Move> moves) {
    System.err.println(moves.size());
    return moves.stream().filter((m) -> {
      try {
        System.out.println(m);
        m.accept(modelSupplier.get());
        return true;
      } catch (IllegalStateException | IllegalArgumentException e) {
        return false;
      }
    }).collect(Collectors.toList());
  }

  /**
   * find effectiveness by comparing the model's state from this.modelSupplier
   * and the state after applying the move
   *
   * @param move a consumer of the model
   * @return an int rating of the effectiveness
   */
  protected abstract int effectiveness(Move move);

  /**
   * to find the best move by reducing all possible moves, choosing the most effective
   * via this.effectiveness. works directly with effectiveness
   *
   * @return - the best move, if it exists
   */
  public Optional<Move> bestMove() {
    return this.allConsideredMoves().stream()
        .reduce(BinaryOperator
                    .maxBy(Comparator.comparingInt(this :: effectiveness)));
  }

  /**
   * To be used in the case that bestMove() returns empty. Assumes a model supplier is passed in
   * where the game is started but not over, i.e. there's at least one playable cell left. This
   * cannot be overridden because every strategy must default the same way, the 0th card in hand at
   * the up-most, left-most (in that order) position.
   *
   * @return the default move standard for all strategies
   */
  public final Move defaultMove() {
    return ((StrategyAbstract) this).bestMove().get();
  }

  protected final List<GridCellReadOnly> modelToCellList(Model model) {
    return Arrays.stream(model.curGrid().readOnlyArray2D()).flatMap(Arrays :: stream).collect(
        Collectors.toList());
  }

}
