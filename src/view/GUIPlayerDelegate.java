package view;


import model.CoachColor;
import model.Model;
import model.Move;
import model.PlayableGameListener;
import utils.Utils;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * To represent a player in the game of three trios as a gui. This is also a listener of a
 * playable game and can propagate exceptions.
 */
public class GUIPlayerDelegate extends GUIPlayerBase implements PlayableGameListener {

  protected PlayableGameListener delegate;

  /**
   * Constructor.
   *
   * @param viewRedHand  the gui of the red hand
   * @param viewBlueHand the gui of the blue hand
   * @param viewGrid     the gui of the grid
   * @param color        the color of the player this represents
   * @param delegate     the delegate to pass move requests onto
   */
  public GUIPlayerDelegate(GUIHandBase viewRedHand,
                           GUIHandBase viewBlueHand,
                           GUIGridBase viewGrid,
                           CoachColor color,
                           PlayableGameListener delegate) {
    super(viewRedHand, viewBlueHand, viewGrid, color);
    setTitle(color.toString());
    this.delegate = delegate;
  }

  /**
   * Processes a move or delegates it to another listener if no exceptions occur.
   *
   * @param callback      a consumer to handle the move
   * @param modelCallable a callable providing the current game model
   */
  @Override
  public void accept(Consumer<Move> callback, Callable<Model> modelCallable) {
    if (!propagateCallable(modelCallable)) {
      delegate.accept(callback, modelCallable);
    }
  }

  /**
   * Attempts to call the provided model and update the view,
   * propagating exceptions if they occur.
   *
   * @param modelCallable a callable that may produce a game model or throw an exception
   * @return true if an exception was caught, false otherwise
   */
  protected boolean propagateCallable(Callable<Model> modelCallable) {
    try {
      updateModel(modelCallable.call());
    } catch (Exception e) {
      Utils.popup(e.getMessage(), this);
      return true;
    }
    return false;
  }

}
