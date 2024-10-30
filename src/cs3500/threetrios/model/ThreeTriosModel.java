package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * to represent a model of the three trios game.
 */
public class ThreeTriosModel implements IModel {
  private Coach coachRed;
  private Coach coachBlue;
  private Coach currentCoach;
  private boolean gameStarted;
  private BattlePhaseReferee referee;
  private Grid grid;
  private Random random;

  public ThreeTriosModel() {

  }

  public ThreeTriosModel(Random r) {
    this.random = r;
  }

  @Override
  public void startGame(Grid grid, List<Card> cards, BattlePhaseReferee referee) {
    if (gameStarted) {
      throw new IllegalStateException("Game has already started");
    }
    if (grid == null || cards == null || referee == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    this.referee = referee;
    this.grid = grid;
    this.coachRed = new Coach(Coach.Color.Red); // we'll pass in the names later
    this.coachBlue = new Coach(Coach.Color.Blue);
    this.currentCoach = coachRed;
    this.gameStarted = true;
    dealCards(cards);
  }

  // red gets card first
  private void dealCards(List<Card> cards) {
    int totalCardCells = grid.arrayRepr().length - grid.getNumHoles();
    int requiredCards = totalCardCells + 1;
    if (cards.size() < requiredCards) {
      throw new IllegalArgumentException("Number of cards must be at least N + 1, where N is the " +
              "number of card cells on the grid");
    }
    List<Card> copy = new ArrayList<>(cards);
    if (cards.size() % 2 != 0) {
      throw new IllegalArgumentException("Number of cards must be even");
    }
    if (random != null) {
      Collections.shuffle(copy);
    }
    for (int i = 0; i < copy.size(); i += 2) {
      Card curRedCard = copy.get(i);
      curRedCard.setCoach(coachRed);
      Card curBlueCard = copy.get(i + 1);
      curBlueCard.setCoach(coachBlue);
      coachRed.addCard(curRedCard);
      coachBlue.addCard(curBlueCard);
    }
  }

  /**
   * Places a card on the grid for the given coach.
   *
   * @param idx   index of current coach's hand
   * @param row   the row index
   * @param col   the column index
   */
  public void placeCard(int idx, int row, int col) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started yet");
    }
    if (isGameOver()) {
      throw new IllegalStateException("Game is over");
    }
    Card curCard = currentCoach.removeCardFromHand(idx);
    AGridCell relevantCell = grid.placeCardOn(row, col, curCard);

    System.out.println("relevant cell" + relevantCell.getCard());
    referee.refereeBattlePhase(relevantCell);
  }

  /**
   * Gets the current coach.
   *
   * @return the current coach
   */
  @Override
  public Coach getCurrentCoach() {
    return this.currentCoach;
  }

  /**
   * Advances to the next coach's turn.
   */
  @Override
  public void nextCoachTurn() {
    if (this.currentCoach == coachRed) {
      this.currentCoach = coachBlue;
    } else {
      this.currentCoach = coachRed;
    }
  }

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    //The game ends when all empty card cells are filled.
    //hands don't have to be empty for the grid to be filled
    return grid.isFull();
  }

  /**
   * Gets the winner of the game.
   *
   * @return the winning coach, or null if it's a tie
   */
  @Override
  public Coach getWinner() {
    if (!isGameOver()) {
      throw new IllegalStateException("Game is not over yet");
    }
    //The winner is determined by counting the number of cards each player owns both on the grid and
    // in their hands.
    return whoHasMoreTotalCards();
  }

  /**
   *
   * @return - coach with more total cards
   * @throws IllegalStateException if grid is not full, or if cards coach is null
   */
  private Coach whoHasMoreTotalCards() {
    int coachRedTotal = coachRed.getHand().size();
    int coachBlueTotal = coachBlue.getHand().size();
    for (AGridCell[] row : grid.arrayRepr()) {
      for (AGridCell cell : row) {
        if (cell.hasCard()) {
          if (cell.getCard().getCoach() == coachRed) {
            coachRedTotal += 1;
          } else if (cell.getCard().getCoach() == coachBlue){
            coachBlueTotal += 1;
          } else {
            throw new IllegalStateException("card should have a coach if this board is full");
          }
        }
      }
    }
    return coachRedTotal > coachBlueTotal ? coachRed : coachBlue; // ternary operator
  }


  public Grid getGrid() {
    return grid;
  }
}