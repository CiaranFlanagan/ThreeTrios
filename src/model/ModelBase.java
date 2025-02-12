package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * To represent a model of the three trios game. Invariant: getCurrentCoach() is never
 * null.
 */
public class ModelBase extends ModelAbstract {

  private boolean gameStarted;
  private Random random;

  /**
   * Constructor.
   */
  public ModelBase() {
    super();
    // default constructor
  }

  /**
   * Constructor.
   * @param r - a random to help randomize dealing cards.
   */
  public ModelBase(Random r) {
    super();
    this.random = r;
  }

  /**
   * Constructor.
   * @return - new TTM
   */
  public static ModelBase create() {
    return new ModelBase();
  }

  @Override
  public void startGame(Grid grid, List<Card> cards, Referee ref) {
    if (gameStarted) {
      throw new IllegalStateException("Game has already started");
    }
    if (grid == null || cards == null || ref == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    int totalCardCells = grid.readOnlyArray2D().length * grid.readOnlyArray2D()[0].length
        - grid.getNumHoles();
    int requiredCards = totalCardCells + 1;
    if (cards.size() < requiredCards) {
      throw new IllegalArgumentException(
          "Number of cards must be at least N + 1, where N is the "
              + "number of card cells on the grid");
    }
    this.grid = grid;
    this.ref = ref;
    this.gameStarted = true;
    dealCards(cards, totalCardCells + 1);
  }

  // red gets card first
  private void dealCards(List<Card> cards, int total) {
    List<Card> copy = new ArrayList<>(cards);
    if (random != null) {
      Collections.shuffle(copy);
    }

    for (int i = 0; i < total; i += 2) {
      Card curRedCard = copy.get(i);
      curRedCard.setCoach(CoachColor.RED);
      Card curBlueCard = copy.get(i + 1);
      curBlueCard.setCoach(CoachColor.BLUE);
      coachesHands.get(CoachColor.RED).add(curRedCard);
      coachesHands.get(CoachColor.BLUE).add(curBlueCard);
    }
  }

  @Override
  public void placeCard(int idx, int row, int col) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started yet");
    }
    if (isGameOver()) {
      throw new IllegalStateException("Game is over");
    }
    if (idx < 0 || idx >= coachesHands.get(currentCoach).size()) {
      throw new IllegalArgumentException("Bad hand index");
    }
    Card curCard = coachesHands.get(currentCoach).remove(idx);
    GridCellVisitable relevantCell = setGridCardAt(row, col, curCard);
    ref.refereeBattlePhase(relevantCell);
    nextCoachTurn();
  }

  private void nextCoachTurn() {
    currentCoach = currentCoach.opponent();
  }

  @Override
  public CoachColor curCoach() {
    return this.currentCoach;
  }

  /**
   * Checks if the game is over.
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameStarted() {
    return gameStarted;
  }

  @Override
  public boolean isGameOver() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started yet");
    }
    //The game ends when all empty card cells are filled.
    //hands don't have to be empty for the grid to be filled
    return curCoachesHands().values()
                            .stream()
                            .map(List :: size)
                            .reduce(0, Integer :: sum)
                            .equals(1);
  }

  @Override
  public CoachColor winner() {
    if (!isGameOver()) {
      throw new IllegalStateException("Game is not over yet");
    }
    //The winner is determined by counting the number of cards each player owns both on
    // the grid
    // and
    // in their hands.
    return whoHasMoreTotalCards();
  }

  /**
   * Determines the coach with more total cards.
   * @return - coach with more total cards
   * @throws IllegalStateException if grid is not full, or if cards coach is null
   */
  private CoachColor whoHasMoreTotalCards() {
    int coachRedTotal = this.coachesHands.get(CoachColor.RED).size();
    int coachBlueTotal = this.coachesHands.get(CoachColor.BLUE).size();
    for (GridCellReadOnly[] row : grid.readOnlyArray2D()) {
      for (GridCellReadOnly cell : row) {
        if (cell.hasCard()) {
          if (cell.getCard().getCoach() == CoachColor.RED) {
            coachRedTotal += 1;
          } else if (cell.getCard().getCoach() == CoachColor.BLUE) {
            coachBlueTotal += 1;
          } else {
            throw new IllegalStateException(
                "card should have a coach if this board is full");
          }
        }
      }
    }
    return coachRedTotal > coachBlueTotal ? CoachColor.RED :
        CoachColor.BLUE; // ternary operator
  }

}
