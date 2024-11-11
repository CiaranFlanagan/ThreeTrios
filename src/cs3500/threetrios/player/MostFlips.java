package cs3500.threetrios.player;

import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Model;

import java.util.function.Function;
import java.util.function.Supplier;

public class MostFlips extends StrategyAbstract {

  public MostFlips(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  /**
   * find effectiveness by comparing the model's state from this.modelSupplier
   * and the state after applying the move
   *
   * @param move - a consumer of the model
   * @return - an int rating of the effectiveness
   */
  @Override
  protected int effectiveness(Move move) {
    Model model = modelSupplier.get();
    Coach.Color color = model.getCurrentCoach().getColor();
    Function<Model, Integer> numBadGuys = (m) -> modelToCellList(model)
            // if card is same color as curCoach then add 0 to acc else add 1 to acc
            .stream().map((c) -> c.hasCard() && c.getCard().getCoachColor() != color ? 1 : 0)
            .reduce(0, Integer::sum);
    int before = numBadGuys.apply(model);
    move.accept(model);
    int after = numBadGuys.apply(model);
    return before - after;
    // we want to see how many bad guys we flipped, so that's before - after
  }

}
