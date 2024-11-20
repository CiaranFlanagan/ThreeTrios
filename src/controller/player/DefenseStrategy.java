package controller.player;

import model.Card;
import model.CardinalDirection;
import model.GridCellReadOnly;
import model.Model;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * A defensive strategy for the Three Trios game.
 * Focuses on minimizing the number of exposed sides when placing a card.
 */
public class DefenseStrategy extends StrategyAbstract {



  /**
   * Evaluates how effective a move is defensively.
   * Considers the number of exposed sides and the attack values of those sides.
   *
   * @param move the move being evaluated
   * @return the effectiveness score of the move
   */
  public int effectiveness(Move move, Supplier<Model> modelSupplier) {
    // alg is all neighbors that aren't there get an A in the algorithm for free
    GridCellReadOnly cell =
        modelSupplier.get().curGrid().readOnlyArray2D()[move.row][move.col];
    Model model = modelSupplier.get();
    List<Card> curCoachHand = model.curCoachesHands().get(model.curCoach());
    Card card = curCoachHand.get(move.handIdx);
    List<CardinalDirection> exposedSides = getExposedSides(move.row, move.col,
                                                           cell);
    return ((4 - exposedSides.size()) * 10) + exposedSides.stream()
                                                          .map((cd) -> card.getAttackValue(cd)
                                                                           .ordinal())
                                                          .reduce(0, Integer :: sum);
  }

  // card cell and is empty
  protected List<CardinalDirection> getExposedSides(int row, int col, GridCellReadOnly cell) {
    return Arrays.stream(CardinalDirection.values())
                 .filter((cd) -> cell.hasNeighborToThe(cd) && cell.getNeighborToThe(cd).canHaveCard()
                     && !cell.getNeighborToThe(cd).hasCard())
                 .collect(Collectors.toList());
  }

}
