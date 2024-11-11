package cs3500.threetrios.player;

import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MostFlips extends StrategyAbstract {
  public MostFlips(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  /**
   * to find all considered moves
   *
   * @return
   */
  @Override
  protected List<Consumer<Model>> allConsideredMoves() {
    Model model = modelSupplier.get();
    int numRows = model.getGrid().readOnly2dCellArray().length;
    int numColumns = model.getGrid().readOnly2dCellArray()[0].length;
    int sizeOfHand = model.getCurrentCoach().getHand().size();
    List<Consumer<Model>> acc = new ArrayList<>();
    IntStream.range(0, numRows).forEach(
            (row) -> IntStream.range(0, numColumns).forEach(
                    (col) -> IntStream.range(0, sizeOfHand).forEach(
                            (id) -> acc.add((m) -> m.placeCard(id, row, col)))));
    return acc;
  }

  /**
   * find effectiveness by comparing the model's state from this.modelSupplier
   * and the state after applying the move
   *
   * @param move - a consumer of the model
   * @return - an int rating of the effectiveness
   */
  @Override
  protected int effectiveness(Consumer<Model> move) {
    Model model = modelSupplier.get();
    Coach.Color color = model.getCurrentCoach().getColor();
    Function<Model, Integer> numBadGuys = (m) -> modelToCellList(model)
            // if card is same color as curCoach then add 0 to acc else add 1 to acc
            .stream().map((c) -> c.hasCard() && c.getCard().getCoachColor() != color ? 1 : 0)
            .reduce(0, Integer::sum);
    int before = numBadGuys.apply(model);
    try {
      move.accept(model);
      int after = numBadGuys.apply(model);
      return before - after;
    } catch (IllegalStateException ex) {
      return -1;
    }
     // we want to see how many bad guys we flipped, so that's before - after
  }
}
