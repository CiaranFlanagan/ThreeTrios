package controller.player;

import model.CoachColor;
import model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GameController {

  Supplier<Model> modelSupplier;
  Model model;
  List<Move> moves;
  Player red;
  Player blue;

  public GameController(Supplier<Model> modelSupplier, Player red, Player blue) {
    this.modelSupplier = modelSupplier;
    model = modelSupplier.get();
    moves = new ArrayList<>();
    this.red = red;
    this.blue = blue;
    red.accept(this :: onMove, modelSupplier);
    prevPlayer().accept(m -> {}, this :: remakeGame);
  }

  public void onMove(Move move) {
    moves.add(move);

    // TODO check errors
    try {
      move.accept(model);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      moves.remove(moves.size() - 1);
      model = remakeGame();
    }

    if (model.isGameOver()) {
      onGameOver();
      prevPlayer().accept(m -> {}, this :: remakeGame);
      nextPlayer().accept(m -> {}, this :: remakeGame);
    } else {
      // TODO
      prevPlayer().accept(m -> {}, this :: remakeGame);
      nextPlayer().accept(this :: onMove, this :: remakeGame);
    }



  }

  private void onGameOver() {
    System.err.println("game over");
    System.err.println("winner: " + model.winner());

  }

  public Model remakeGame() {
    Model copy = modelSupplier.get();
    moves.forEach(m -> m.accept(copy));
    return copy;
  }

  private Player prevPlayer() {
    return coachColorToPlayer(model.curCoach().opponent());
  }

  private Player nextPlayer() {
    return coachColorToPlayer(model.curCoach());
  }

  private Player coachColorToPlayer(CoachColor coach) {
    return coach == CoachColor.RED ? red : blue;
  }


}
