package controller.player;

import model.CoachColor;
import model.Model;
import model.Move;
import model.PlayableGameListener;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * Represents a player in the game of Three Trios. A player is a listener of a callable
 * model and also calls back what to do with a move.
 */
public class ControlPlayer implements PlayableGameListener {

  private final CoachColor coachColor;
  private final PlayableGameListener delegate;

  /**
   * Constructor.
   *
   * @param coachColor the coach color of this player
   * @param delegate the delegate to propagate notifications
   */
  public ControlPlayer(CoachColor coachColor, PlayableGameListener delegate) {
    this.coachColor = coachColor;
    this.delegate = delegate;
  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Callable<Model> modelSupplier) {
    delegate.accept(moveConsumer, modelSupplier);
  }

  @Override
  public String toString() {
    return coachColor.toString();
  }

}
