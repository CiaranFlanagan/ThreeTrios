package cs3500.threetrios.player;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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

public class DefenseStrategy extends StrategyAbstract {

  public DefenseStrategy(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  public int effectiveness(Move move) {
    // alg is all neighbors that aren't there get an A in the algorithm for free
    GridCellReadOnly cell =
            modelSupplier.get().getGrid().readOnly2dCellArray()[move.getRow()][move.getCol()];
    Card card = modelSupplier.get().getCurrentCoach().getHand().get(move.getHandIdx());
    List<CardinalDirection> exposedSides = getExposedSides(move.getRow(), move.getCol(),
                                                           cell);
    return ((4 - exposedSides.size()) * 10) +
            exposedSides.stream().map((cd) -> card.getAttackValue(cd).ordinal())
                    .reduce(0, Integer::sum);
  }

  // card cell and is empty
  protected List<CardinalDirection> getExposedSides(int row, int col, GridCellReadOnly cell) {
    return Arrays.stream(CardinalDirection.values())
            .filter((cd) -> cell.hasNeighborToThe(cd) &&
                    cell.getNeighborToThe(cd).canHaveCard() &&
                    !cell.getNeighborToThe(cd).hasCard()
            ).collect(Collectors.toList());
  }

}
