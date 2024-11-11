package cs3500.threetrios.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardinalDirection;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Grid;
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
    GridCellReadOnly[][] grid = model.getGrid().readOnly2dCellArray();
    int numRows = grid.length;
    int numCols = grid[0].length;
    int sizeOfHand = model.getCurrentCoach().getHand().size();
    List<Consumer<Model>> moves = new ArrayList<>();

    List<int[]> cornerPosns = Arrays.asList(new int[]{0, 0},
            new int[]{0, numCols - 1},
            new int[]{numRows - 1, 0},
            new int[]{numRows - 1, numCols - 1});

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
    Model initialModel = modelSupplier.get();
    Model model = modelSupplier.get();

    try {
      move.accept(model);
    } catch (IllegalStateException ex) {
      return -1;
    }
    Coach.Color color = model.getCurrentCoach().getColor();
    List<GridCellReadOnly> initialCells = modelToCellList(initialModel);
    List<GridCellReadOnly> afterCells = modelToCellList(model);

    return IntStream.range(0, initialCells.size())
            .filter(i -> {
              GridCellReadOnly initialCell = initialCells.get(i);
              GridCellReadOnly afterCell = afterCells.get(i);
              return !initialCell.hasCard() && afterCell.hasCard()
                      && afterCell.getCard().getCoachColor() == color;
            })
            .mapToObj(i -> {
              int numColumns = model.getGrid().readOnly2dCellArray().length;
              int row = i / numColumns;
              int col = i % numColumns;
              Card card = afterCells.get(i).getCard();
              List<CardinalDirection> exposedSides = getExposedSides(row, col, model.getGrid());
              int hardnessScore = exposedSides.stream()
                      .mapToInt(dir -> card.getAttackValue(dir).fromAttackValue())
                      .sum();
              return hardnessScore;
            })
            .findFirst()
            .orElse(-1);
  }

  private List<CardinalDirection> getExposedSides(int row, int col, Grid grid) {
    List<CardinalDirection> exposedSides = new ArrayList<>();
    if (row == 0) {
      exposedSides.add(CardinalDirection.SOUTH);
    } else if (row == grid.readOnly2dCellArray()[0].length - 1) {
      exposedSides.add(CardinalDirection.NORTH);
    }
    if (col == 0) {
      exposedSides.add(CardinalDirection.EAST);
    } else if (col == grid.readOnly2dCellArray().length - 1) {
      exposedSides.add(CardinalDirection.WEST);
    }
    return exposedSides;
  }

}
