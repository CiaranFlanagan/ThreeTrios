package cs3500.threetrios.player;

import cs3500.threetrios.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class CornerStrategy extends DefenseStrategy {

  public CornerStrategy(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  // for each corner, play each card from hand
  protected List<Move> allConsideredMoves() {
    Model model = modelSupplier.get();
    List<Move> acc = new ArrayList<>();
    int lastRow = model.curGrid().readOnlyArray2D().length - 1;
    int lastCol = model.curGrid().readOnlyArray2D()[0].length - 1;
    Consumer<Function<Integer, Move>> func = (f) ->
            IntStream.range(0, model.curCoachesHands().get(model.curCoach()).size())
                    .forEach((handId) -> acc.add(f.apply(handId)));
    func.accept((handId) -> Move.of(0, 0, handId));
    func.accept((handId) -> Move.of(lastRow, 0, handId));
    func.accept((handId) -> Move.of(lastRow, lastCol, handId));
    func.accept((handId) -> Move.of(lastRow, lastCol, handId));
    return filterOutIllegalMoves(acc);
  }

}
