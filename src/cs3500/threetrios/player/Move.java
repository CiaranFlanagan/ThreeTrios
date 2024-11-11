package cs3500.threetrios.player;

import cs3500.threetrios.model.Model;

import java.util.function.Consumer;

public final class Move implements Consumer<Model> {
  private final int row;
  private final int col;
  private final int handIdx;

  /**
   * constructor
   * @param row
   * @param col
   * @param handIdx
   */
  private Move(int row, int col, int handIdx) {
    this.row = row;
    this.col =col;
    this.handIdx = handIdx;
  }

  /**
   * static constructor
   * @param row
   * @param col
   * @param handIdx
   * @return
   */
  public static Move of(int row, int col, int handIdx) {
    return new Move(row, col, handIdx);
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public int getHandIdx() {
    return this.handIdx;
  }


  /**
   * Performs this operation on the given argument.
   *
   * @param model the input argument
   */
  @Override
  public void accept(Model model) {
    model.placeCard(row, col, handIdx);
  }
}
