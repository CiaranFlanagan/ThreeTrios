package controller;

import model.CoachColor;
import model.GameListener;
import model.GamePlayer;
import model.Model;
import model.Move;
import view.GameView;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * To represent a controller of a player of Three Trios.
 */
public abstract class AbstractControlPlayer implements GameListener {

  protected CoachColor color;
  protected GameView view;
  protected GamePlayer player;

  /**
   * Constructor
   * @param color color of the player
   * @param view view of the game
   * @param player player of the game
   */
  public AbstractControlPlayer(CoachColor color, GameView view, GamePlayer player) {
    this.color = color;
    this.view = view;
    this.player = player;
  }
}
