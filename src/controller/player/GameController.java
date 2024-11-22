package controller.player;

import model.CoachColor;
import model.Model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public class GameController {

  Supplier<Model> modelSupplier;
  Model model;
  Deque<Move> moves;
  Player red;
  Player blue;

  public GameController(Supplier<Model> modelSupplier, Player red, Player blue) {
    this.modelSupplier = modelSupplier;
    model = modelSupplier.get();
    moves = new ArrayDeque<>();
    this.red = red;
    this.blue = blue;
    curPlayer().accept(this :: onMove, modelSupplier);
    prevPlayer().accept(m -> {}, this :: remakeGame);
  }

  public void onMove(Move move) {
    moves.add(move);

    // TODO check errors
    try {
      move.accept(model);
    } catch (Exception e) {
      System.err.println(e.getMessage());
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


  private Player prevPlayer() {
    return coachColorToPlayer(model.curCoach().opponent());
  }

  private Player curPlayer() {
    return coachColorToPlayer(model.curCoach());
  }

  private Player coachColorToPlayer(CoachColor coach) {
    return coach == CoachColor.RED ? red : blue;
  }


}
