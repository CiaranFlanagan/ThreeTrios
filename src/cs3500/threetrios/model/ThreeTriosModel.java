package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * to represent a model of the three trios game.
 */
public class ThreeTriosModel implements IModel {
  private Coach coachRed;
  private Coach coachBlue;
  private boolean gameStarted;
  private boolean gameOver;
  private BattlePhaseReferee referee;
  private Coach currentCoach;
  private Grid grid;
  private List<Card> cards;

  @Override
  public void startGame(Coach coach, Card card, int row, int col) {
    if (gameStarted) {
      throw new IllegalStateException("Game has already started");
    }
    if (coachRed == null || coachBlue == null || grid == null || cards == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.coachRed = coachRed;
    this.coachBlue = coachBlue;
    this.currentCoach = coachRed;
    this.gameStarted = true;
    this.gameOver = false;
    dealCards(cards);
  }
//helper
  private void dealCards(List<Card> cards) {
    List<Card> copy = new ArrayList<>(cards);
    if (cards.size() % 2 != 0) {
      throw new IllegalArgumentException("Number of cards must be even");
    }
    Collections.shuffle(copy);
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
    if (gameOver) {
      throw new IllegalStateException("Game is over");
    }
    Coach curCoach = getCurrentCoach();
    Card curCard = curCoach.removeCardFromHand(idx);
    AGridCell relevantCell = grid.placeCardOn(row, col, curCard);
    referee.refereeBattlePhase(relevantCell);
    nextTurn();
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
  public void nextTurn() {
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
    if (coachRed.getHand().isEmpty() && coachBlue.getHand().isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the winner of the game.
   *
   * @return the winning coach, or null if it's a tie
   */
  @Override
  public Coach getWinner() {
    return null;
  }
}
