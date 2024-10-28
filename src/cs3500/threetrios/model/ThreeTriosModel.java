package cs3500.threetrios.model;

import java.util.Collections;
import java.util.List;

public class ThreeTriosModel implements GameModel{
  private GamePlayer playerRed;
  private GamePlayer playerBlue;
  private GamePlayer currentPlayer;
  private BoardGrid grid;
  private boolean gameStarted;
  private boolean gameOver;
  /**
   * Starts the game with the given players, grid, and cards.
   *
   * @param player1 the first player
   * @param player2 the second player
   * @param grid    the game grid
   * @param cards   the list of cards to deal
   */
  @Override
  public void startGame(GamePlayer player1, GamePlayer player2, BoardGrid grid, List<Card> cards) {
    if (gameStarted) {
      throw new IllegalStateException("Game has already started");
    }
    if (player1 == null || player2 == null || grid == null || cards == null) {
      throw new IllegalArgumentException("Players, grid, and cards cannot be null");
    }
    this.playerRed = player1;
    this.playerBlue = player2;
    this.currentPlayer = playerRed;
    this.grid = grid;
    this.gameStarted = true;
    this.gameOver = false;

    dealCards(cards);
  }

  private void dealCards(List<Card> cards) {
    if (cards.size() % 2 != 0) {
      throw new IllegalArgumentException("Number of cards must be even");
    }
    Collections.shuffle(cards);
    int half = cards.size() / 2;
    for (int i = 0; i < half; i++) {
      playerRed.addCard(cards.get(i));
    }
    for (int i = half; i < cards.size(); i++) {
      playerBlue.addCard(cards.get(i));
    }
  }

  /**
   * Places a card on the grid for the given player.
   *
   * @param player the player making the move
   * @param card   the card to place
   * @param row    the row index
   * @param col    the column index
   */
  @Override
  public void placeCard(GamePlayer player, Card card, int row, int col) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started yet");
    }
    if (gameOver) {
      throw new IllegalStateException("Game is over");
    }
    if (player != currentPlayer) {
      throw new IllegalArgumentException("It's not " + player.getName() + "'s turn");
    }
    if (!player.getHand().contains(card)) {
      throw new IllegalArgumentException("Player does not have this card");
    }
    BoardCell cell = grid.getCell(row, col);
    if (cell.getType() != CellType.CARD_CELL) {
      throw new IllegalArgumentException("Cannot place a card on a hole");
    }
    CardCell cardCell = (CardCell) cell;
    if (cardCell.hasCard()) {
      throw new IllegalArgumentException("Cell already has a card");
    }
    cardCell.placeCard(card, player);
    player.removeCard(card);
    executeBattle(cardCell, player);
    checkGameOver();
  }

  private void checkGameOver() {
  }

  private void executeBattle(CardCell cardCell, GamePlayer player) {
  }

  /**
   * Gets the current player.
   *
   * @return the current player
   */
  @Override
  public GamePlayer getCurrentPlayer() {
    return null;
  }

  /**
   * Advances to the next player's turn.
   */
  @Override
  public void nextTurn() {

  }

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Gets the winner of the game.
   *
   * @return the winning player, or null if it's a tie
   */
  @Override
  public GamePlayer getWinner() {
    return null;
  }

  /**
   * Renders the current game state as a string.
   *
   * @return the game state string
   */
  @Override
  public String renderGameState() {
    return null;
  }
}
