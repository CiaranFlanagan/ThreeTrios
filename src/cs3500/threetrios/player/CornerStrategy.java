package cs3500.threetrios.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.Model;

public class CornerStrategy extends DefenseStrategy {

  public CornerStrategy(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  protected List<Move> allConsideredMoves() {
    Model model = modelSupplier.get();
    List<Move> acc = new ArrayList<>();
    BiFunction<Integer, Integer, List<Move>> pos2Move =
            (row, col) -> {
              List<Move> innerAcc = new ArrayList<>();
              IntStream.range(0, model.getCurrentCoach().getHand().size()).forEach(
                      (handId) -> innerAcc.add(Move.of(row, col, handId)));
              return innerAcc;
            };
    acc.addAll(pos2Move.apply(0, 0));
    acc.addAll(pos2Move.apply(0, model.getGrid().readOnly2dCellArray()[0].length));
    acc.addAll(pos2Move.apply(model.getGrid().readOnly2dCellArray().length - 1,0));
    acc.addAll(pos2Move.apply(model.getGrid().readOnly2dCellArray().length - 1,
                              model.getGrid().readOnly2dCellArray()[0].length - 1));
    acc.stream().filter((move) -> {
      try {
        move.accept(modelSupplier.get());
        return true;
      } catch (Exception ignored) {
        return false;
      }
    }).collect(Collectors.toList());
    return acc;
  }

  // card cell and is empty
  private List<CardinalDirection> getExposedSides1(int row, int col, Grid grid) {
    return Arrays.stream(CardinalDirection.values())
            .filter((cd) -> {
              GridCellReadOnly cell = grid.readOnly2dCellArray()[row][col];
              return cell.hasNeighborToThe(cd) ?
                      (cell.getNeighborToThe(cd).canHaveCard() &&
                              !cell.getNeighborToThe(cd).hasCard()) :
                      false;
            }).collect(Collectors.toList());
  }
}
