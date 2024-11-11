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

public class ControllerBase implements Controller {
  private Model model;
  private View view;
  private Supplier<Model> modelSupplier;
  private Supplier<Grid> gridSupplier;
  private Supplier<List<Card>> cardsSupplier;
  private Supplier<Referee> refereeSupplier;
  private final List<Consumer<Model>> moves;

  private ControllerBase() {
    this.moves = new ArrayList<>();
  }

  @Override
  public void createUsing(Supplier<Grid> gridSupplier,
                          Supplier<List<Card>> cardsSupplier,
                          Supplier<Model> modelSupplier,
                          Supplier<Referee> refereeSupplier,
                          View view) {
    this.gridSupplier = gridSupplier;
    this.cardsSupplier = cardsSupplier;
    this.modelSupplier = modelSupplier;
    this.refereeSupplier = refereeSupplier;
    this.model = supplyModel().get();
    this.view = view;
  }

  /**
   * @return - a model that has the same initial conditions as this controller's model
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
