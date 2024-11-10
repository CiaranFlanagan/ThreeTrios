package cs3500.threetrios.player;

import cs3500.threetrios.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MostFlips extends StrategyAbstract {
  public MostFlips(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  /**
   * to find all possible moves
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
    for (int curRow = 0; curRow < numRows; curRow += 1) {
      for (int curCol = 0; curCol < numColumns; curCol += 1) {
        for (int curPlaceInHand = 0; curPlaceInHand < sizeOfHand; curPlaceInHand += 1) {
          int finalCurPlaceInHand = curPlaceInHand;
          int finalCurRow = curRow;
          int finalCurCol = curCol;
          acc.add((m) -> m.placeCard(finalCurPlaceInHand, finalCurRow, finalCurCol));
        }
      }
    }
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
    return 0;
  }
}
