package cs3500.threetrios.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModelForView implements ModelReadOnly {
  ModelForView delegate;
  public ModelForView(ModelForView delegate) {
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

  public int numRows() {
    return delegate.numRows();
  }

  public int numCols() {
    return delegate.numCols();
  }

  public Optional<Card> cardAt(int row, int col) {
    return delegate.cardAt(row, col);
  }

  public Optional<Coach> ownerAt(int row, int col) {
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
  public int score(Coach coach) {
    return delegate.score(coach);
  }

}
