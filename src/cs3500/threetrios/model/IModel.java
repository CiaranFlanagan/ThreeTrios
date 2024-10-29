package cs3500.threetrios.model;

import cs3500.threetrios.model.done.TTCard;

import java.util.List;

/**
 * Represents the game model.
 */
public interface IModel {
  /**
   * Starts the game with the given players, grid, and cards.
   *
   * @param player1 the first player
   * @param player2 the second player
   * @param grid    the game grid
   * @param cards   the list of cards to deal
   */
  void startGame(IPlayer player1, IPlayer player2, GridBoard grid, List<TTCard> cards);

  /**
   * Places a card on the grid for the given player.
   *
   * @param player the player making the move
   * @param card   the card to place
   * @param row    the row index
   * @param col    the column index
   */
  void placeCard(IPlayer player, TTCard card, int row, int col);

  /**
   * Gets the current player.
   *
   * @return the current player
   */
  IPlayer getCurrentPlayer();

  /**
   * Advances to the next player's turn.
   */
  void nextTurn();

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Gets the winner of the game.
   *
   * @return the winning player, or null if it's a tie
   */
  IPlayer getWinner();

}

