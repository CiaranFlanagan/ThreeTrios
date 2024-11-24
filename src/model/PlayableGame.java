package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

/**
 * Represents/manages the execution of a game between two players. Handles moves, assists
 * in managing games state, and listens for eah player
 */
public class PlayableGame {

  private final Supplier<Model> modelSupplier;
  private final Deque<Move> moves;
  private final GameListener red;
  private final GameListener blue;
  private Model cache;


  /**
   * Constructs a playable game with a model supplier and listeners for each player.
   * @param modelSupplier supplier to create a new instance of the game model
   * @param red           listener for the red player
   * @param blue          listener for the blue player
   */
  public PlayableGame(Supplier<Model> modelSupplier,
                      GameListener red,
                      GameListener blue) {
    this.modelSupplier = modelSupplier;
    this.moves = new ArrayDeque<>();
    this.red = red;
    this.blue = blue;
    this.cache = modelSupplier.get();
    curPlayerListener().accept(this :: onMove, this :: remakeGame);
    otherPlayerListener().accept(m -> {
    }, this :: remakeGame);
  }

  /**
   * Applies a move and updates the game state, handling any errors that occur.
   * @param move the move to apply
   */
  public void onMove(Move move) {
    try {
      move.accept(remakeGame());
    } catch (Exception e) {
      curPlayerListener().accept(this :: onMove, () -> {
        throw e;
      });
      curPlayerListener().accept(this :: onMove, this :: remakeGame);
    }

    move.accept(cache);
    moves.add(move);

    if (cache.isGameOver()) {
      RuntimeException onGameOver =
          new RuntimeException("\ngame over\nwinner: " + cache.winner());
      otherPlayerListener().accept(m -> {
      }, () -> {
        throw onGameOver;
      });
      otherPlayerListener().accept(m -> {}, () -> cache);
      curPlayerListener().accept(m -> {
      }, () -> {
        throw onGameOver;
      });
    }

    otherPlayerListener().accept(m -> {}, this :: remakeGame);
    curPlayerListener().accept(this :: onMove, this :: remakeGame);

  }

  /**
   * Creates a new game model and replays all moves to replicate the current state.
   * @return a new game model in the same state as the current one
   */
  public Model remakeGame() {
    Model copy = modelSupplier.get();
    moves.forEach(m -> m.accept(copy));
    return copy;
  }

  private GameListener otherPlayerListener() {
    return coachColorToPlayer(cache.curCoach().opponent());
  }

  private GameListener curPlayerListener() {
    return coachColorToPlayer(cache.curCoach());
  }

  private GameListener coachColorToPlayer(CoachColor coach) {
    return coach == CoachColor.RED ? red : blue;
  }

}
