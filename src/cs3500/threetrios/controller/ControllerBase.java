package cs3500.threetrios.controller;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * ControllerBase serves as the base implementation of the Controller interface for the Three Trios
 * game. This class is responsible for managing the game state, initializing the model with specific
 * configurations, and tracking moves to maintain consistency across models.
 */
public class ControllerBase implements Controller {
  private Supplier<Model> modelSupplier;
  private Supplier<Grid> gridSupplier;
  private Supplier<List<Card>> cardsSupplier;
  private Supplier<Referee> refereeSupplier;
  private final List<Consumer<Model>> moves;

  public ControllerBase() {
    this.moves = new ArrayList<>();
  }

  @Override
  public void createUsing(Supplier<Grid> gridSupplier,
                          Supplier<List<Card>> cardsSupplier,
                          Supplier<Model> modelSupplier,
                          Supplier<Referee> refereeSupplier,
                          View<?> view) {
    this.gridSupplier = gridSupplier;
    this.cardsSupplier = cardsSupplier;
    this.modelSupplier = modelSupplier;
    this.refereeSupplier = refereeSupplier;
    Model model = supplyModel().get();
  }

  /**
   * Provides a supplier that returns a new instance of a Model configured with the initial game
   * state. This includes setting up the grid, cards, and referee, and applying any moves that
   * were previously recorded.
   *
   * @return a Supplier<Model> that, when invoked, creates and initializes a new Model instance
   *         with the specified grid, cards, referee, and a replay of recorded moves.
   */
  public Supplier<Model> supplyModel() {
    return () -> {
      final Model model = modelSupplier.get();
      model.startGame(gridSupplier.get(),
                      cardsSupplier.get(),
                      refereeSupplier.get());
      moves.forEach((move) -> move.accept(model));
      return model;
    };
  }

}
