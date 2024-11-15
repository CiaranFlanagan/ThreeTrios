package cs3500.threetrios.model.player;

import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Model;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Strategy that maximizes the number of opponent's cards flipped.
 * Evaluates moves by comparing the number of opponent-controlled cards before and after the move.
 */
public class MostFlips extends StrategyAbstract {

  /**
   * Constructor.
   *
   * @param modelSupplier supplies the current game model
   */
  public MostFlips(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  @Override
  protected int effectiveness(Move move) {
    Model model = modelSupplier.get();
    Coach color = model.curCoach();
    Function<Model, Integer> numBadGuys = (m) -> modelToCellList(model)
            // if card is same color as curCoach then add 0 to acc else add 1 to acc
            .stream().map((c) -> c.hasCard() && c.getCard().getCoach() != color ? 1 : 0)
            .reduce(0, Integer::sum);
    int before = numBadGuys.apply(model);
    move.accept(model);
    int after = numBadGuys.apply(model);
    return before - after;
    // we want to see how many bad guys we flipped, so that's before - after
  }

}
