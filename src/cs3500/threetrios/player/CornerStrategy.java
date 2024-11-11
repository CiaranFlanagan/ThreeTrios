package cs3500.threetrios.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cs3500.threetrios.model.GridCellReadOnly;
import cs3500.threetrios.model.Model;

public class CornerStrategy extends StrategyAbstract {

  public CornerStrategy(Supplier<Model> modelSupplier) {
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
    GridCellReadOnly[][] grid =  model.getGrid().readOnly2dCellArray();
    int numRows = grid.length;
    int numCols = grid[0].length;
    int sizeOfHand = model.getCurrentCoach().getHand().size();
    List<Consumer<Model>> moves = new ArrayList<>();

    List<int[]> cornerPosns = Arrays.asList(new int[] {0, 0},
            new int[] {0, numCols - 1},
            new int[] {numRows - 1, 0},
            new int[] {numRows - 1, numCols - 1});

    moves = cornerPosns.stream().filter((posn) -> {
      int row = posn[0];
      int col = posn[1];
      GridCellReadOnly cell = grid[row][col];
      return cell.canHaveCard() && !cell.hasCard();
    }).flatMap(posn -> IntStream.range(0, sizeOfHand).mapToObj(id -> (Consumer<Model>) m -> m.placeCard(id, posn[0], posn[1]))
    ).collect(Collectors.toList());
    return moves;
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
