package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

/**
 * Represents/manages the execution of a game between two players. Handles moves, assists in
 * managing games state, and listens for eah player
 */
public class PlayableGame {

  private Supplier<Model> modelSupplier;
  private Model model;
  private Deque<Move> moves;
  private PlayableGameListener red;
  private PlayableGameListener blue;

  /**
   * Constructs a playable game with a model supplier and listeners for each player.
   *
   * @param modelSupplier supplier to create a new instance of the game model
   * @param red           listener for the red player
   * @param blue          listener for the blue player
   */
  public PlayableGame(Supplier<Model> modelSupplier,
                      PlayableGameListener red,
                      PlayableGameListener blue) {
    this.modelSupplier = modelSupplier;
    model = modelSupplier.get();
    moves = new ArrayDeque<>();
    this.red = red;
    this.blue = blue;
    curPlayer().accept(this::onMove, this::remakeGame);
    prevPlayer().accept(m -> {
    }, this::remakeGame);
  }

  /**
   * Applies a move and updates the game state, handling any errors that occur.
   *
   * @param move the move to apply
   */
  public void onMove(Move move) {
    moves.add(move);

    // TODO check errors
    try {
      move.accept(model);
    } catch (Exception e) {
      moves.removeLast();
      model = remakeGame();
      curPlayer().accept(this::onMove, () -> {
        throw e;
      });
      curPlayer().accept(this::onMove, this::remakeGame);
    }

    if (model.isGameOver()) {
      RuntimeException onGameOver =
          new RuntimeException("\ngame over\nwinner: " + model.winner());
      prevPlayer().accept(m -> {
      }, () -> {
        throw onGameOver;
      });
      curPlayer().accept(m -> {
      }, () -> {
        throw onGameOver;
      });
    }
    prevPlayer().accept(m -> {
    }, this::remakeGame);
    curPlayer().accept(this::onMove, this::remakeGame);

  }

  /**
   * Creates a new game model and replays all moves to replicate the current state.
   *
   * @return a new game model in the same state as the current one
   */
  public Model remakeGame() {
    Model copy = modelSupplier.get();
    moves.forEach(m -> m.accept(copy));
    return copy;
  }


  private PlayableGameListener prevPlayer() {
    return coachColorToPlayer(model.curCoach().opponent());
  }

  private PlayableGameListener curPlayer() {
    return coachColorToPlayer(model.curCoach());
  }

  private PlayableGameListener coachColorToPlayer(CoachColor coach) {
    return coach == CoachColor.RED ? red : blue;
  }

}
