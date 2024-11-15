package cs3500.threetrios.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>a read only interface for a three trios model. the objects that inheritors return to fulfill
 * these requirements need not be the exact reference. they only need to have the same
 * observations as the inheritor's private objects make at that point in the game timeline.</p>
 * <p>
 * Example:
 * State 1 : Model is started<br>
 * Grid g1 = model.getGrid();<br>
 * State 2: Model has 5 moves played on it<br>
 * Grid g2 = model.getGrid();<br>
 * g1 != g2;<br>
 * g1.(some observation) =? g2.(some observation)   -- indeterminate<br>
 * </p>
 */
public interface ModelReadOnly {
  /**
   * Gets the current coach.
   *
   * @return the current coach
   */
  Coach curCoach();

  /**
   * to return the hand of the given coach.
   *
   * @return
   */
  Map<Coach, List<Card>> curCoachesHands();

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  boolean isGameStarted();


  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Gets the winner of the game.
   *
   * @return the winning coach, or null if it's a tie
   */
  Coach winner();

  /**
   * Returns the current grid at this state.
   *
   * @return the grid
   */
  Grid curGrid();

  int numRows();

  int numCols();

  Optional<Card> cardAt(int row, int col);

  Optional<Coach> ownerAt(int row, int col);

  boolean canPlayAt(int row, int col);

  int numFlippedIfPlaced(Card card, int row, int col);

  int score(Coach coach);
}
