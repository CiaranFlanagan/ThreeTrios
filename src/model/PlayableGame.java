package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public class PlayableGame {

  private Supplier<Model> modelSupplier;
  private Model model;
  private Deque<Move> moves;
  private PlayableGameListener red;
  private PlayableGameListener blue;

  public PlayableGame(Supplier<Model> modelSupplier,
                      PlayableGameListener red,
                      PlayableGameListener blue) {
    this.modelSupplier = modelSupplier;
    model = modelSupplier.get();
    moves = new ArrayDeque<>();
    this.red = red;
    this.blue = blue;
    curPlayer().accept(this :: onMove, this :: remakeGame);
    prevPlayer().accept(m -> {}, this :: remakeGame);
  }

  public void onMove(Move move) {
    moves.add(move);

    // TODO check errors
    try {
      move.accept(model);
    } catch (Exception e) {
      moves.removeLast();
      model = remakeGame();
      curPlayer().accept(this :: onMove, () -> {throw e;});
      curPlayer().accept(this :: onMove, this :: remakeGame);
    }

    if (model.isGameOver()) {
      RuntimeException onGameOver =
          new RuntimeException("\ngame over\nwinner: " + model.winner());
      prevPlayer().accept(m -> {}, () -> {throw onGameOver;});
      curPlayer().accept(m -> {}, () -> {throw onGameOver;});
    }
    prevPlayer().accept(m -> {}, this :: remakeGame);
    curPlayer().accept(this :: onMove, this :: remakeGame);

  }

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
