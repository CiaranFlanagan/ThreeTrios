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
  }

  public void onMove(Move move) {
    if (model.isGameOver()) {
      System.err.println("game over");
      System.err.println("winner: " + model.winner());
    }
    moves.add(move);

    // TODO check errors
    try {
      move.accept(model);
      System.err.println(move);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      moves.remove(moves.size() - 1);
    }

    // TODO
    prevPlayer();
    nextPlayer().accept(this :: onMove, this :: remakeGame);

  }

  public Model remakeGame() {
    Model copy = modelSupplier.get();
    moves.forEach(m -> m.accept(copy));
    if (moves.size() > 13) {
      System.err.println(moves);
    }
    return copy;
  }

  private Player prevPlayer() {
    return coachColorToPlayer(model.curCoach().opponent());
  }

  private Player nextPlayer() {
    System.out.println("Current coach: " + model.curCoach());
    System.out.println("\n\n\n");
    return coachColorToPlayer(model.curCoach());
  }

  private Player coachColorToPlayer(CoachColor coach) {
    return coach == CoachColor.RED ? red : blue;
  }


}
