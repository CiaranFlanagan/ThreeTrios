package cs3500.threetrios.test.player;

import cs3500.threetrios.model.ModelBase;
import java.util.List;
/**
 * A mock implementation of the ModelBase class used for testing the MostFlips strategy in the
 * Three Trios game. MockMostFlips logs card placements instead of interacting with an actual game
 * grid, allowing verification of method calls and parameters during testing.
 */
public class MockMostFlips extends ModelBase {

  List<List<Integer>> log;

  /**
   * Constructs a MockMostFlips with a specified log to track the row and column coordinates
   * of placed cards.
   *
   * @param log a list of lists used to record each placement's row and column values
   */
  public MockMostFlips(List<List<Integer>> log) {
    this.log = log;
  }

  @Override
  public void placeCard(int idx, int row, int col) throws IllegalStateException,
          IllegalArgumentException {
    log.add(List.of(row, col));
  }

}
