package copy.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A read-only view of the game model, designed for use by the view component in the Three
 * Trios game. ModelForView acts as a wrapper around another model to provide limited
 * access to game data without allowing modifications.
 */
public class ModelForView implements ModelReadOnly {

  ModelReadOnly delegate;

  /**
   * Constructs a ModelForView that delegates calls to another ModelForView instance.
   * @param delegate the ModelForView instance to which this class delegates method calls
   */
  public ModelForView(ModelReadOnly delegate) {
    this.delegate = delegate;
  }

  @Override
  public CoachColor curCoach() {
    return delegate.curCoach();
  }

  @Override
  public Map<CoachColor, List<Card>> curCoachesHands() {
    return delegate.curCoachesHands();
  }

  @Override
  public boolean isGameStarted() {
    return delegate.isGameStarted();
  }

  @Override
  public boolean isGameOver() {
    return delegate.isGameOver();
  }

  @Override
  public CoachColor winner() {
    return delegate.winner();
  }

  @Override
  public Grid curGrid() {
    return delegate.curGrid();
  }

  public int numRows() {
    return delegate.numRows();
  }

  public int numCols() {
    return delegate.numCols();
  }

  public Optional<Card> cardAt(int row, int col) {
    return delegate.cardAt(row, col);
  }

  public Optional<CoachColor> ownerAt(int row, int col) {
    return delegate.ownerAt(row, col);
  }

  public boolean canPlayAt(int row, int col) {
    return delegate.canPlayAt(row, col);
  }

  @Override
  public int numFlippedIfPlaced(Card card, int row, int col) {
    return delegate.numFlippedIfPlaced(card, row, col);
  }

  @Override
  public int score(CoachColor coach) {
    return delegate.score(coach);
  }

}
