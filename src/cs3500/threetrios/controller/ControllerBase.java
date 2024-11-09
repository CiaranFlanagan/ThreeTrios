package cs3500.threetrios.controller;

import com.sun.jdi.ArrayType;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ControllerBase {
  private Model model;
  private View view;
  private Supplier<Model> modelSupplier;
  private Supplier<Scanner> gridSupplier;
  private Supplier<Scanner> cardSupplier;
  private Referee referee;
  private final List<Consumer<Model>> moves;

  private ControllerBase() {
    this.moves = new ArrayList<>();
  }

  /**
   * Creates a controller with a model using the following initial conditions.
   * @param gridSupplier
   * @param cardSupplier
   * @param modelSupplier
   * @param referee
   * @return
   */
  public void createUsing(Supplier<Scanner> gridSupplier,
                                           Supplier<Scanner> cardSupplier,
                                           Supplier<Model> modelSupplier,
                                           Referee referee) {
    this.gridSupplier = cardSupplier;
    this.cardSupplier = gridSupplier;
    this.modelSupplier = modelSupplier;
    this.model = supplyModel().get();
    this.referee = referee;
  }

  /**
   *
   * @return - a model that has the same initial conditions as this controller's model
   */
  public Supplier<Model> supplyModel() {
    Model model = modelSupplier.get();
    model.startGame(ConfigGrid.scannerToGrid(gridSupplier.get()),
                               ConfigCard.scannerToCardList(cardSupplier.get()),
                               referee);
    moves.forEach((move) -> move.accept(model));
    return () -> model;
  }



}
