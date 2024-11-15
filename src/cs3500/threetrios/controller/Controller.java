package cs3500.threetrios.controller;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.view.View;

import java.util.List;
import java.util.function.Supplier;

/**
 * To control the flow of the game and handle input and output.
 */
public interface Controller {

  /**
   * Creates a controller with a model using the following initial conditions.
   *
   * @param gridSupplier    - supplier of a grid
   * @param cardsSupplier   - supplier of a list of cards
   * @param modelSupplier   - supplier of a model
   * @param refereeSupplier - supplier of a referee
   * @param view            - the view
   */
  void createUsing(Supplier<Grid> gridSupplier,
                   Supplier<List<Card>> cardsSupplier,
                   Supplier<Model> modelSupplier,
                   Supplier<Referee> refereeSupplier,
                   View<?> view);
}
