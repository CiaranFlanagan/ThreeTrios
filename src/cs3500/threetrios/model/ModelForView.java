package cs3500.threetrios.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModelForView implements ModelReadOnly {
  Model delegate;
  public ModelForView(Model delegate) {
    this.delegate = delegate;
  }

  @Override
  public Coach curCoach() {
    return delegate.curCoach();
  }

  @Override
  public Map<Coach, List<Card>> curCoachesHands() {
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
  public Coach winner() {
    return delegate.winner();
  }

  @Override
  public Grid curGrid() {
    return delegate.curGrid();
  }

  @Override
  public int numRows() {
    return delegate.numRows();
  }

  @Override
  public int numCols() {
    return delegate.numCols();
  }

  @Override
  public Optional<Card> cardAt(int row, int col) {
    return delegate.cardAt(row, col);
  }

  @Override
  public Optional<Coach> ownerAt(int row, int col) {
    return delegate.ownerAt(row, col);
  }

  @Override
  public boolean canPlayAt(int row, int col) {
    return delegate.canPlayAt(row, col);
  }

  @Override
  public int numFlippedIfPlaced(Card card, int row, int col) {
    return delegate.numFlippedIfPlaced(card, row, col);
  }

  @Override
  public int score(Coach coach) {
    return delegate.score(coach);
  }

}
