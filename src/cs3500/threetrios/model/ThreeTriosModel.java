package cs3500.threetrios.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
    if (!player.getHand().contains(card)) { //might have to override equals in Card
      throw new IllegalArgumentException("Player does not have this card");
    }
    BoardCell cell = grid.getCell(row, col);
    if (cell.getType() != CellType.CARD_CELL) {
      throw new IllegalArgumentException("Cannot place a card on a hole");
    }
    CardCell cardCell = (CardCell) cell; //need to cast here I think, might be another way
    if (cardCell.hasCard()) {
      throw new IllegalArgumentException("Cell already has a card");
    }
    cardCell.placeCard(card, player); //change this method name in CardCell
    player.removeCard(card);
    executeBattle(cardCell, player);
    checkGameOver();
  }

  private void checkGameOver() {
    if (playerRed.getHand().isEmpty() && playerBlue.getHand().isEmpty()) {
      gameOver = true;
    }
  }

  private void executeBattle(CardCell placedCell, GamePlayer player) {
    Queue<CardCell> toProcess = new LinkedList<>();
    Set<CardCell> processed = new HashSet<>();
    toProcess.add(placedCell);

    while (!toProcess.isEmpty()) {
      CardCell cell = toProcess.poll();
      processed.add(cell);
      //loops through all neighbors of the cell(should be 4 total)
      for (Map.Entry<Direction, BoardCell> entry : cell.getNeighbors().entrySet()) {
        Direction direction = entry.getKey();
        BoardCell neighborCell = entry.getValue();
        //if the neighbors a Card cell...
        if (neighborCell.getType() == CellType.CARD_CELL) {
          CardCell neighbor = (CardCell) neighborCell; //cast b/c neighbor cell is a BoardCell
          //if the neighbor has a card and the owner is different get the values of each card
          if (neighbor.hasCard() && neighbor.getOwner() != currentPlayer) {
            int attackValue = cell.getCard().getAttackValue(direction).getNumericValue();
            int defenseValue = neighbor.getCard().getAttackValue(direction.opposite()).getNumericValue();

            //if the attack val is greater than the neighbors attack value, set the owner
            if (attackValue > defenseValue) {
              neighbor.setOwner(currentPlayer);
              //if the neighbor is not already in the toProcess queue, add it so we don't process
              // so we can process it later.
              if (!processed.contains(neighbor)) {
                toProcess.add(neighbor);
              }
            }
          }
        }
      }
    }
  }

  /**
   * Gets the current player.
   *
   * @return the current player
   */
  @Override
  public GamePlayer getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Advances to the next player's turn.
   */
  @Override
  public void nextTurn() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started yet");
    }
    if (gameOver) {
      throw new IllegalStateException("Game is over");
    }
    currentPlayer = (currentPlayer == playerRed) ? playerBlue : playerRed;
  }

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Gets the winner of the game.
   *
   * @return the winning player, or null if it's a tie
   */
  @Override
  public GamePlayer getWinner() {
    if (!gameOver) {
      return null;
    }
    int redCount = 0;
    int blueCount = 0;
    for (BoardCell cell : grid.getAllCells()) {
      if (cell.getType() == CellType.CARD_CELL) {
        CardCell cardCell = (CardCell) cell;
        if (cardCell.hasCard()) {
          if (cardCell.getOwner() == playerRed) {
            redCount++;
          } else if (cardCell.getOwner() == playerBlue) {
            blueCount++;
          }
        }
      }
    }
    if (redCount > blueCount) {
      return playerRed;
    } else if (blueCount > redCount) {
      return playerBlue;
    } else {
      return null; //handle with other methods
    }
  }

  /**
   * Renders the current game state as a string.
   *
   * @return the game state string
   */
  @Override
  public String renderGameState() {
    StringBuilder sb = new StringBuilder();
    sb.append("Player: ").append(currentPlayer.getName()).append("\n");
    for (int row = 0; row < grid.getRows(); row++) {
      for (int col = 0; col < grid.getColumns(); col++) {
        BoardCell cell = grid.getCell(row, col);
        if (cell.getType() == CellType.HOLE) {
          sb.append(" ");
        } else if (cell.getType() == CellType.CARD_CELL) {
          CardCell cardCell = (CardCell) cell;
          if (cardCell.hasCard()) {
            String initial = cardCell.getOwner().getName().substring(0, 1).toUpperCase();
            sb.append(initial);
          } else {
            sb.append("_");
          }
          sb.append(" ");
        }
      }
      sb.append("\n");
    }

    sb.append("Hand:\n");
    for (Card card : currentPlayer.getHand()) {
      sb.append(card.getName());
      for (Direction dir : CardinalDirection.values()) {
        AttackValue attackValue = card.getAttackValue(dir);
        sb.append(" ").append(attackValue.toString());
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
