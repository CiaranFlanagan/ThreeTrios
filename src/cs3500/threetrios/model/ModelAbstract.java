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
 * An abstract class representing the shared functionality of models in the Three Trios game.
 * ModelAbstract provides a foundation for managing the game state, including the grid, player
 * hands, current coach, and moves.
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

  /**
   * Returns a copy of the current coaches' hands with cards copied to prevent modification.
   *
   * @return an unmodifiable map of coaches and their respective hands
   */
  @Override
  public Map<Coach, List<Card>> curCoachesHands() {
    Map<Coach, List<Card>> temp = new EnumMap<>(coachesHands);
    for (Coach coach : temp.keySet()) {
      temp.put(coach, Collections.unmodifiableList(
              coachesHands.get(coach).stream().map(Card::copy).collect(Collectors.toList())));
    }
    return temp;
  }

  /**
   * Returns the current state of the grid.
   *
   * @return the current grid object
   */
  @Override
  public Grid curGrid() {
    return grid;
  }

  /**
   * Returns the number of rows in the grid.
   *
   * @return the number of rows
   */
  public int numRows() {
    return this.grid.readOnlyArray2D().length;
  }

  /**
   * Returns the number of columns in the grid.
   *
   * @return the number of columns
   */
  public int numCols() {
    return this.grid.readOnlyArray2D()[0].length;
  }

  /**
   * Returns the card at the specified row and column if one exists.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return an Optional containing the card at the specified position, or empty if no card exists
   */
  public Optional<Card> cardAt(int row, int col) {
    checkRowCol(row, col);
    GridCellReadOnly cell = this.grid.readOnlyArray2D()[row][col];
    if (cell.hasCard()) {
      return Optional.of(cell.getCard());
    }
    return Optional.empty();
  }

  /**
   * Returns the coach who owns the card at the specified row and column, if a card is present.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return an Optional containing the coach at the specified position, or empty if no card exists
   */
  public Optional<Coach> ownerAt(int row, int col) {
    checkRowCol(row, col);
    GridCellReadOnly cell = this.grid.readOnlyArray2D()[row][col];
    if (cell.hasCard()) {
      return Optional.of(cell.getCard().getCoach());
    }
    return Optional.empty();
  }

  /**
   * Determines if a card can be placed at the specified position.
   *
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return true if a card can be placed at the specified position, false otherwise
   */
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
