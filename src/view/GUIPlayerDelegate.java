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
    setName(color.toString());
    this.delegate = delegate;
  }

  @Override
  public void accept(Consumer<Move> callback, Callable<Model> modelCallable) {
    if (!propagateCallable(modelCallable)) {
      delegate.accept(callback, modelCallable);
    }
  }

  /**
   * @param modelCallable the callable model which may be an exception
   * @return true if there was an exception, false otherwise
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
