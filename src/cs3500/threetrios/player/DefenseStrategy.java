package cs3500.threetrios.player;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.Model;

/**
 * The DefenseStrategy class represents a defensive strategy for the Three Trios game,
 * focusing on positioning cards to maximize protection by considering the exposure of card sides.
 * This strategy evaluates the effectiveness of moves based on how many sides of a placed card
 * are exposed to neighboring cells, and the Attack Value of those exposed sides.
 */
public class DefenseStrategy extends StrategyAbstract {

  public DefenseStrategy(Supplier<Model> modelSupplier) {
    super(modelSupplier);
  }

  /**
   * Calculates the effectiveness of a move based on the number of exposed sides after placing a
   * card. The greater the sum of the Attack Values on exposed sides, plus the value of the
   * unexposed sides, the greater defense a card has.
   *
   * @param move the Move being evaluated for its defensive effectiveness
   * @return an integer score representing the effectiveness of the move
   */
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
