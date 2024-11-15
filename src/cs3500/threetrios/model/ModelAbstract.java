package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Abstract base class for models in the Three Trios game.
 * Provides functionality for managing game state, including grid, player hands,
 * current coach, and gameplay mechanisms.
 */
public abstract class ModelAbstract implements Model {
  protected Grid grid;
  protected Map<Coach, List<Card>> coachesHands;
  protected Coach currentCoach;
  protected List<Consumer<ModelAbstract>> moves;
  protected Referee ref;

  protected ModelAbstract() {
    Map<Coach, List<Card>> temp = new EnumMap<>(Coach.class);
    temp.put(Coach.RED, new ArrayList<>());
    temp.put(Coach.BLUE, new ArrayList<>());
    // only red and blue, so just get and then update the hand accordingly
    coachesHands = Collections.unmodifiableMap(temp);
    currentCoach = Coach.RED;
  }

  @Override
  public Map<Coach, List<Card>> curCoachesHands() {
    Map<Coach, List<Card>> temp = new EnumMap<>(coachesHands);
    for (Coach coach : temp.keySet()) {
      temp.put(coach, Collections.unmodifiableList(
              coachesHands.get(coach).stream().map(Card::copy).collect(Collectors.toList())));
    }
    return temp;
  }

  @Override
  public Grid curGrid() {
    return grid;
  }

  @Override
  public int numRows() {
    return this.grid.readOnlyArray2D().length;
  }

  @Override
  public int numCols() {
    return this.grid.readOnlyArray2D()[0].length;
  }

  @Override
  public Optional<Card> cardAt(int row, int col) {
    checkRowCol(row, col);
    GridCellReadOnly cell = this.grid.readOnlyArray2D()[row][col];
    if (cell.hasCard()) {
      return Optional.of(cell.getCard());
    }
    return Optional.empty();
  }

  @Override
  public Optional<Coach> ownerAt(int row, int col) {
    checkRowCol(row, col);
    GridCellReadOnly cell = this.grid.readOnlyArray2D()[row][col];
    if (cell.hasCard()) {
      return Optional.of(cell.getCard().getCoach());
    }
    return Optional.empty();
  }

  @Override
  public boolean canPlayAt(int row, int col) {
    checkRowCol(row, col);
    GridCellReadOnly cell = this.grid.readOnlyArray2D()[row][col];
    return cell.canHaveCard() && !cell.hasCard();
  }

  @Override
  public int numFlippedIfPlaced(Card card, int row, int col) {
    checkRowCol(row, col);
    int scoreOfOppBefore = score(currentCoach.opponent());
    Grid copy = grid.copy();
    GridCellVisitable cellOfInterest = copy.placeCardOn(row, col, card);
    cellOfInterest.acceptBattlePhase(ref);
    int scoreOfOppAfter = score(currentCoach.opponent(), copy);
    return scoreOfOppBefore - scoreOfOppAfter;
  }

  @Override
  public int score(Coach coach) {
    return score(coach, grid);
  }

  private int score(Coach coach, Grid grid) {
    return (int) Arrays.stream(grid.readOnlyArray2D())
            .flatMap(Arrays::stream)
            .filter(gc -> gc.hasCard() && gc.getCard().getCoach() == coach)
            .count();
  }

  protected GridCellVisitable setGridCardAt(int row, int col, Card card) {
    return grid.placeCardOn(row, col, card);
  }

  private void checkRowCol(int row, int col) {
    if (row < 0 || col < 0 || row >= this.grid.readOnlyArray2D().length ||
            col >= this.grid.readOnlyArray2D()[0].length) {
      throw new IllegalArgumentException("Row and col must be Natural Number and within the " +
                                                 "range of the grid");
    }

  }

}
