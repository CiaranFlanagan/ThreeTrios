package controller.strategy;

import model.GamePlayer;
import model.GridCellReadOnly;
import model.Model;
import model.Move;
import model.GameListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Abstract base class for game strategies in the Three Trios game. Defines methods for
 * evaluating moves and finding the most effective one.
 */
public abstract class StrategyAbstract implements GamePlayer {

  /**
   * To find the best move by reducing all possible moves, choosing the most effective via
   * this.effectiveness. works directly with effectiveness
   * @return - the best move, if it exists
   */
  public Optional<Move> bestMove(Supplier<Model> modelSupplier) {
    return allConsideredMoves(modelSupplier).stream()
                                            .reduce(BinaryOperator.maxBy(
                                                Comparator.comparingInt(
                                                    move -> effectiveness(move,
                                                                          modelSupplier))));
  }

  /**
   * To produce a list of all moves that meet this strategy's specifications and don't
   * cause the model to error. All possible moves on board by default.
   * @return a list of all considered moves by this strategy.
   */
  protected List<Move> allConsideredMoves(Supplier<Model> modelSupplier) {
    return allPossibleMovesOnBoard(modelSupplier);
  }

  /**
   * find effectiveness by comparing the model's state from this.modelSupplier and the
   * state after applying the move
   * @param move a consumer of the model
   * @return an int rating of the effectiveness
   */
  protected abstract int effectiveness(Move move, Supplier<Model> modelSupplier);

  private List<Move> allPossibleMovesOnBoard(Supplier<Model> modelSupplier) {
    Model model = modelSupplier.get();
    int numRows = model.curGrid().readOnlyArray2D().length;
    int numColumns = model.curGrid().readOnlyArray2D()[0].length;
    int sizeOfHand = model.curCoachesHands().get(model.curCoach()).size();
    List<Move> acc0 = new ArrayList<>();
    for (int row = 0; row < numRows; row += 1) {
      for (int col = 0; col < numColumns; col += 1) {
        for (int idx = 0; idx < sizeOfHand; idx += 1) {
          acc0.add(Move.create(idx, row, col));
        }
      }
    }
    return filterOutIllegalMoves(acc0, modelSupplier);
  }

  /**
   * To filter out any move that causes the model to error.
   * @param moves the list of moves to filter
   * @return a list of moves that will not cause the model to error.
   */
  protected List<Move> filterOutIllegalMoves(List<Move> moves,
                                             Supplier<Model> modelSupplier) {
    return moves.stream().filter((m) -> {
      try {
        m.accept(modelSupplier.get());
        return true;
      } catch (IllegalStateException | IllegalArgumentException e) {
        return false;
      }
    }).collect(Collectors.toList());
  }

  /**
   * To be used in the case that bestMove() returns empty. Assumes a model supplier is
   * passed in where the game is started but not over, i.e. there's at least one playable
   * cell left. This cannot be overridden because every strategy must default the same
   * way, the 0th card in hand at the up-most, left-most (in that order) position.
   * @return the default move standard for all strategies. returns null if game is over so
   *     that it can be called but not used when a model's game is over.
   */
  public final Move defaultMove(Supplier<Model> modelSupplier) {
    return allPossibleMovesOnBoard(modelSupplier).stream()
                                                 .reduce(BinaryOperator.maxBy(
                                                     Comparator.comparingInt(((m) -> 0))))
                                                 .orElse(null);
  }

  protected final List<GridCellReadOnly> modelToCellList(Model model) {
    return Arrays.stream(model.curGrid().readOnlyArray2D())
                 .flatMap(Arrays :: stream)
                 .collect(Collectors.toList());
  }

  @Override
  public final void accept(Consumer<Move> moveConsumer, Supplier<Model> modelSupplier) {
    if (modelSupplier.get() == null) {
      return;
    }
    moveConsumer.accept(bestMove(modelSupplier).orElse(defaultMove(modelSupplier)));
  }


}
