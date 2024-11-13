package cs3500.threetrios.player;

import cs3500.threetrios.model.Model;

import java.util.function.Consumer;

/**
 * Represents a move in the Three Trios game, defined by a row and column position on the grid
 * and an index representing a card from the player's hand. A Move can be applied to a Model
 * to place a card at the specified location.
 */
public final class Move implements Consumer<Model> {
  private final int row;
  private final int col;
  private final int handIdx;

  /**
   * Constructs a Move with the specified row, column, and hand index.
   *
   * @param row     the row index on the grid
   * @param col     the column index on the grid
   * @param handIdx the index of the card in the player's hand
   */
  private Move(int row, int col, int handIdx) {
    this.row = row;
    this.col = col;
    this.handIdx = handIdx;
  }

  /**
   * Creates a new Move instance with the specified row, column, and hand index.
   *
   * @param row the row index on the grid
   * @param col the column index on the grid
   * @param handIdx the index of the card in the player's hand
   * @return a new Move instance representing the specified move
   */
  public static Move of(int row, int col, int handIdx) {
    return new Move(row, col, handIdx);
  }

  /**
   * Gets the row index for this move.
   *
   * @return the row index
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Gets the column index for this move.
   *
   * @return the column index
   */
  public int getCol() {
    return this.col;
  }

  /**
   * Gets the hand index for this move.
   *
   * @return the hand index
   */
  public int getHandIdx() {
    return this.handIdx;
  }


  /**
   * Executes this move on the given model by placing the specified card from the player's hand
   * at the designated grid cell.
   *
   * @param model the game Model on which to execute this move
   */
  @Override
  public void accept(Model model) {
    model.placeCard(row, col, handIdx);
  }
}
