package cs3500.threetrios.player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import cs3500.threetrios.model.Model;

/**
 * The CornerStrategy class is a defensive strategy that attempts to place cards in the corners of the game grid.
 * It generates all possible moves by playing each card in the hand to each corner of the grid.
 */
public class CornerStrategy extends DefenseStrategy {

  public CornerStrategy(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  // for each corner, play each card from hand
  protected List<Move> allConsideredMoves() {
    Model model = modelSupplier.get();
    List<Move> acc = new ArrayList<>();
    int lastRow = model.getGrid().readOnly2dCellArray().length - 1;
    int lastCol = model.getGrid().readOnly2dCellArray()[0].length - 1;
    Consumer<Function<Integer, Move>> func = (f) ->
            IntStream.range(0, model.getCurrentCoach().getHand().size())
                    .forEach((handId) -> acc.add(f.apply(handId)));
    func.accept((handId) -> Move.of(0, 0, handId));
    func.accept((handId) -> Move.of(lastRow, 0, handId));
    func.accept((handId) -> Move.of(lastRow, lastCol, handId));
    func.accept((handId) -> Move.of(lastRow, lastCol, handId));
    return acc.stream().filter((move) -> {
      try {
        move.accept(modelSupplier.get());
        return true;
      } catch (Exception ignored) {
        return false;
      }
    }).collect(Collectors.toList());
  }

}