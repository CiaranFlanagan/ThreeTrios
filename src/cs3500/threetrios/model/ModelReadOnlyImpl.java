package cs3500.threetrios.model;

import java.util.List;
import java.util.Map;

public class ModelReadOnlyImpl implements ModelReadOnly {
  Model delegate;
  public ModelReadOnlyImpl(Model delegate) {
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
}
