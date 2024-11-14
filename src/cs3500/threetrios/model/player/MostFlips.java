package cs3500.threetrios.model.player;

import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Model;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The MostFlips strategy aims to maximize the number of opponent's cards flipped
 * by placing a card in the most advantageous position on the grid.
 * This strategy calculates the effectiveness of a move based on the difference in opponent's cards
 * before and after a move is made, selecting moves that yield the highest number of flips.
 */
public class MostFlips extends StrategyAbstract {

  public MostFlips(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  /**
   * Find effectiveness by comparing the model's state from this.modelSupplier
   * and the state after applying the move
   * @param move - a consumer of the model
   * @return - an int rating of the effectiveness
   */
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
