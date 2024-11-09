package cs3500.threetrios.player;

import cs3500.threetrios.model.Model;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public abstract class StrategyAbstract<S extends Strategy<S>> implements Strategy<S> {
  protected int idx;
  protected int row;
  protected int col;

  /**
   * to find the strategy that is more effective, either this or that.
   * @param that - the sibling strategy we are comparing with.
   * @param modelSupplier - the supplier of the model.
   * @return - the more effective strategy.
   */
  public final S betterOf(S that, Supplier<Model> modelSupplier) {
    Comparator<S> comp = Comparator.comparingInt((s) -> s.effectiveness(modelSupplier));
    BinaryOperator<S> better = BinaryOperator.maxBy(comp);
    return better.apply((S) this, that);
  }
  /**
   * Performs this move on the given argument.
   *
   * @param model the input argument
   */
  @Override
  public final void accept(Model model) {
    model.placeCard(idx, row, col);
  }

  protected final int validMoveOr(S s, Model model, int orElse) {
    try {
      s.accept(model);
    } catch (Throwable ignored) {

    }
    return orElse;
  }

}
